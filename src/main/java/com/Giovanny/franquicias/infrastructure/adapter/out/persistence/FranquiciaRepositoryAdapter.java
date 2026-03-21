package com.Giovanny.franquicias.adapter.out.persistence;

import org.springframework.stereotype.Component;
import com.Giovanny.franquicias.domain.port.out.FranquiciaRepositoryPort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import com.Giovanny.franquicias.domain.model.Franquicia;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class FranquiciaRepositoryAdapter implements FranquiciaRepositoryPort {

    private final FranquiciaR2dbcRepository repository;

    @Override
    public Mono<Franquicia> save(Franquicia franquicia) {
        return repository.save(franquicia);
    }

    @Override
    public Mono<Franquicia> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Franquicia> findAll() {
        return repository.findAll();
    }
    
}
