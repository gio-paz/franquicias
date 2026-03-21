package com.Giovanny.franquicias.adapter.out.persistence;

import reactor.core.publisher.Flux;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.Giovanny.franquicias.domain.model.Producto;

public interface ProductoR2dbcRepository extends ReactiveCrudRepository<Producto, Long> {

    Flux<Producto> findBySucursalId(Long sucursalId);
    
}
