package com.ufn.fortuneAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ufn.fortuneAPI.entities.FortuneCookie;

import java.util.UUID;

@Repository
public interface FortuneCookieRepository extends JpaRepository<FortuneCookie, UUID> {}
