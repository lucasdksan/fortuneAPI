package com.ufn.fortuneAPI.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ufn.fortuneAPI.entities.FortunePhrase;

@Repository
public interface FortunePhraseRepository extends JpaRepository<FortunePhrase, UUID>{}
