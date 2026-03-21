package com.Giovanny.franquicias.domain.port.out;

import com.Giovanny.franquicias.domain.model.Franquicia;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FranquiciaRepositoryPort {
    
    Mono<Franquicia> save(Franquicia franquicia);
    Mono<Franquicia> findById(Long id);
    Flux<Franquicia> findAll();

}
    
