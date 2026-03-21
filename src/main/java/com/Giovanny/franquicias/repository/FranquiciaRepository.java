package com.Giovanny.franquicias.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.Giovanny.franquicias.model.Franquicia;

@Repository
public interface FranquiciaRepository extends ReactiveCrudRepository<Franquicia, Long> {
}