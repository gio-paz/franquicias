package com.Giovanny.franquicias.adapter.out.persistence;

import org.springframework.stereotype.Component;
import com.Giovanny.franquicias.domain.model.Sucursal;
import com.Giovanny.franquicias.domain.port.out.SucursalRepositoryPort;
import reactor.core.publisher.Flux;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SucursalRepositoryAdapter implements SucursalRepositoryPort {

    private final SucursalR2dbcRepository repository;

    @Override
    public Mono<Sucursal> save(Sucursal sucursal) {
        return repository.save(sucursal);
    }

    @Override
    public Mono<Sucursal> findById(Long id) {
        return repository.findById(id);
    }
    
    @Override
    public Flux<Sucursal> findByFranquiciaId(Long franquiciaId) {
        return repository.findByFranquiciaId(franquiciaId);
    }
    
}
