package com.ufn.fortuneAPI.services;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ufn.fortuneAPI.controllers.dtos.CreateFortunePhraseDTO;
import com.ufn.fortuneAPI.entities.FortunePhrase;
import com.ufn.fortuneAPI.exceptions.ResourceNotFoundException;
import com.ufn.fortuneAPI.repositories.FortunePhraseRepository;

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
}
