package com.Giovanny.franquicias.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import com.Giovanny.franquicias.model.Producto;
import reactor.core.publisher.Flux;

@Repository
public interface ProductoRepository extends ReactiveCrudRepository<Producto, Long> {

    Flux<Producto> findBySucursalId(Long sucursalId);
}