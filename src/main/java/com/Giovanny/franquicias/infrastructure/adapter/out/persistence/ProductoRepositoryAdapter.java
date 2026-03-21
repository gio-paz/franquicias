package com.Giovanny.franquicias.adapter.out.persistence;

import org.springframework.stereotype.Component;
import com.Giovanny.franquicias.domain.model.Producto;
import com.Giovanny.franquicias.domain.port.out.ProductoRepositoryPort;
import reactor.core.publisher.Flux;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductoRepositoryAdapter implements ProductoRepositoryPort {

    private final ProductoR2dbcRepository repository;

    @Override
    public Mono<Producto> save(Producto producto) {
        return repository.save(producto);
    }

    @Override
    public Mono<Producto> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }

    @Override
    public Flux<Producto> findBySucursalId(Long sucursalId) {
        return repository.findBySucursalId(sucursalId);
    }
}
