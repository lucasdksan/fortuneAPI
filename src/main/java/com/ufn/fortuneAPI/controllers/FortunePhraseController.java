package com.ufn.fortuneAPI.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufn.fortuneAPI.controllers.dtos.CreateFortunePhraseDTO;
import com.ufn.fortuneAPI.entities.FortunePhrase;
import com.ufn.fortuneAPI.services.FortunePhraseService;

@RestController
@RequestMapping("/v1/fortune-phrase")
public class FortunePhraseController {
    private FortunePhraseService fortunePhraseService;

    public FortunePhraseController(FortunePhraseService fortunePhraseService) {
        this.fortunePhraseService = fortunePhraseService;
    }

    @PostMapping
    public ResponseEntity<FortunePhrase> createFortunePhrase(@RequestBody CreateFortunePhraseDTO body) {
        var fortunePharaseId = this.fortunePhraseService.createFortunePhrase(body);
        
        return ResponseEntity.created(URI.create("/v1/fortune-phrase/"+fortunePharaseId.toString())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FortunePhrase> getFortunePhrase(@PathVariable("id") String id) {
        var fortunePhrase = this.fortunePhraseService.getFortunePhrase(id);
        return ResponseEntity.ok(fortunePhrase);
    }
}
