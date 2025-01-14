package com.ufn.fortuneAPI.services;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ufn.fortuneAPI.controllers.dtos.CreateFortunePhraseDTO;
import com.ufn.fortuneAPI.controllers.dtos.UpdateFortunePhraseDTO;
import com.ufn.fortuneAPI.entities.FortunePhrase;
import com.ufn.fortuneAPI.exceptions.ResourceNotFoundException;
import com.ufn.fortuneAPI.repositories.FortunePhraseRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FortunePhraseService {
    private FortunePhraseRepository fortunePhraseRepository;

    public FortunePhraseService(FortunePhraseRepository fortunePhraseRepository) {
        this.fortunePhraseRepository = fortunePhraseRepository;
    }

    public UUID createFortunePhrase(CreateFortunePhraseDTO createFortunePhraseDTO) {
        if (createFortunePhraseDTO.content() == null || createFortunePhraseDTO.content().isBlank()) {
            throw new IllegalArgumentException("Content cannot be null or blank.");
        }

        var entity = new FortunePhrase(null, createFortunePhraseDTO.content(), createFortunePhraseDTO.author(), Instant.now(), null);
        return this.fortunePhraseRepository.save(entity).getId();
    }

    public FortunePhrase getFortunePhrase(String id) {
        return this.fortunePhraseRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new ResourceNotFoundException("Fortune Phrase not found for ID: " + id));
    }

    public List<FortunePhrase> listFortunePhrase() {
        return this.fortunePhraseRepository.findAll();
    }

    public void updateFortunePhrase(String id, UpdateFortunePhraseDTO body) {
        var fortunePhraseEntity = this.fortunePhraseRepository.findById(UUID.fromString(id));

        if (fortunePhraseEntity.isPresent()) {
            var entity = fortunePhraseEntity.get();

            if (body.content() != null) {
                entity.setContent(body.content());
            }

            if (body.author() != null) {
                entity.setAuthor(body.author());
            }

            if(body.author() != null || body.content() != null) {
                entity.setUpdatedAt(Instant.now());
            }

            if (entity.getContent() == null || entity.getContent().isEmpty()) {
                throw new IllegalArgumentException("Content cannot be null or empty");
            }

            this.fortunePhraseRepository.save(entity);
        } else {
            throw new EntityNotFoundException("FortunePhrase not found with id: " + id);
        }
    }

    public void deleteFortunePhrase(String id){
        var fortunePhraseIsExist = this.fortunePhraseRepository.existsById(UUID.fromString(id));
        
        if(fortunePhraseIsExist) {
            this.fortunePhraseRepository.deleteById(UUID.fromString(id));
        }
    }
}
