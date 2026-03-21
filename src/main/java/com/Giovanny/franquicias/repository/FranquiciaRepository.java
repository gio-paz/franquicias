package com.Giovanny.franquicias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Giovanny.franquicias.model.Franquicia;

@Repository
public interface FranquiciaRepository extends JpaRepository<Franquicia, Long> {
}