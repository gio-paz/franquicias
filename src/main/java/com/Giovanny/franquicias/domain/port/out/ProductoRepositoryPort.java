package com.Giovanny.franquicias.domain.port.out;

import com.Giovanny.franquicias.domain.model.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductoRepositoryPort {

    Mono<Producto> save(Producto producto);
    Mono<Producto> findById(Long id);
    Flux<Producto> findBySucursalId(Long sucursalId);
    Mono<Void> deleteById(Long id);

}
