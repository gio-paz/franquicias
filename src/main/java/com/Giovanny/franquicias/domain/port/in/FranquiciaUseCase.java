package com.Giovanny.franquicias.domain.port.in;

import com.Giovanny.franquicias.domain.model.Franquicia;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FranquiciaUseCase {
    
    Mono<Franquicia> agregar(Franquicia franquicia);
    Mono<Franquicia> actualizarNombre(Long id, String nuevoNombre);
    Flux<Franquicia> listarTodas();

}
