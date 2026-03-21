package com.Giovanny.franquicias.adapter.out.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.Giovanny.franquicias.domain.model.Sucursal;

import reactor.core.publisher.Flux;

public interface SucursalR2dbcRepository extends ReactiveCrudRepository<Sucursal, Long> {

    Flux<Sucursal> findByFranquiciaId(Long franquiciaId);
    
}
