package com.Giovanny.franquicias.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import com.Giovanny.franquicias.model.Sucursal;
import reactor.core.publisher.Flux;

@Repository
public interface SucursalRepository extends ReactiveCrudRepository<Sucursal, Long> {
    Flux<Sucursal> findByFranquiciaId(Long franquiciaId);
}