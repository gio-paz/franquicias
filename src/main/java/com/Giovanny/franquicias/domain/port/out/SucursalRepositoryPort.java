package com.Giovanny.franquicias.domain.port.out;

import com.Giovanny.franquicias.domain.model.Sucursal;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SucursalRepositoryPort {

    Mono<Sucursal> save(Sucursal sucursal);
    Mono<Sucursal> findById(Long id);  
    Flux<Sucursal> findByFranquiciaId(Long franquiciaId);
    
}
