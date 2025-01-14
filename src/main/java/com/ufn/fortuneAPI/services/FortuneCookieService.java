package com.ufn.fortuneAPI.services;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ufn.fortuneAPI.controllers.dtos.CreateFortuneCookieDTO;
import com.ufn.fortuneAPI.entities.FortuneCookie;
import com.ufn.fortuneAPI.entities.FortunePhrase;
import com.ufn.fortuneAPI.exceptions.ResourceNotFoundException;
import com.ufn.fortuneAPI.repositories.FortuneCookieRepository;
import com.ufn.fortuneAPI.repositories.FortunePhraseRepository;

@Service
public class FortuneCookieService {
    private FortuneCookieRepository fortuneCookieRepository;
    private FortunePhraseRepository fortunePhraseRepository;
    
    public FortuneCookieService(
        FortuneCookieRepository fortuneCookieRepository,
        FortunePhraseRepository fortunePhraseRepository
    ) {
        this.fortuneCookieRepository = fortuneCookieRepository;
        this.fortunePhraseRepository = fortunePhraseRepository;
    }

    public UUID createFortuneCookie(CreateFortuneCookieDTO createFortuneCookieDTO) {
        FortunePhrase fortunePhrase = fortunePhraseRepository.findById(UUID.fromString(createFortuneCookieDTO.id_phrase()))
            .orElseThrow(() -> new ResourceNotFoundException("Phrase not found for ID: " + createFortuneCookieDTO.id_phrase()));
        FortuneCookie entity = new FortuneCookie();
        entity.setName(createFortuneCookieDTO.name());
        entity.setCreatedAt(Instant.now());
        entity.setUpdatedAt(null);
        entity.setFortunePhrase(fortunePhrase);
        
        return this.fortuneCookieRepository.save(entity).getId();
    }

    public FortuneCookie getFortuneCookie(String id) {
        return this.fortuneCookieRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new ResourceNotFoundException("Fortune Phrase not found for ID: " + id));
    }

    public List<FortuneCookie> listFortuneCookie() {
        return this.fortuneCookieRepository.findAll();
    }

    public void deleteFortuneCookie(String id) {
        var fortuneCookieIsExist = this.fortuneCookieRepository.existsById(UUID.fromString(id));

        if(fortuneCookieIsExist) {
            this.fortuneCookieRepository.deleteById(UUID.fromString(id));
        }
    }
}
