package com.ufn.fortuneAPI.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufn.fortuneAPI.controllers.dtos.CreateFortuneCookieDTO;
import com.ufn.fortuneAPI.entities.FortuneCookie;
import com.ufn.fortuneAPI.services.FortuneCookieService;

@RestController
@RequestMapping("/v1/fortune-cookie")
public class FortuneCookieController {
    private FortuneCookieService fortuneCookieService;

    public FortuneCookieController(FortuneCookieService fortuneCookieService) {
        this.fortuneCookieService = fortuneCookieService;
    }

    @PostMapping
    public ResponseEntity<FortuneCookie> createFortuneCookie(@RequestBody CreateFortuneCookieDTO body){
        var fortuneCookieId = this.fortuneCookieService.createFortuneCookie(body);
        
        return ResponseEntity.created(URI.create("/v1/fortune-fortune-cookie/"+fortuneCookieId.toString())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FortuneCookie> getFortuneCookie(@PathVariable("id") String id) {
        var fortuneCookie = this.fortuneCookieService.getFortuneCookie(id);
        return ResponseEntity.ok(fortuneCookie);
    }
}
