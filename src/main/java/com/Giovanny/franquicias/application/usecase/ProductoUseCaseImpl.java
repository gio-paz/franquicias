package com.Giovanny.franquicias.application.usecase;

import org.springframework.stereotype.Service;
import com.Giovanny.franquicias.domain.model.Producto;
import com.Giovanny.franquicias.domain.port.in.ProductoUseCase;
import com.Giovanny.franquicias.domain.port.out.ProductoRepositoryPort;
import com.Giovanny.franquicias.domain.port.out.SucursalRepositoryPort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ProductoUseCaseImpl implements ProductoUseCase {

    private final ProductoRepositoryPort productoRepositoryPort;
    private final SucursalRepositoryPort sucursalRepositoryPort;

    @Override
    public Mono<Producto> agregar(Long sucursalId, Producto producto) {
        producto.setSucursalId(sucursalId);
        return productoRepositoryPort.save(producto);
    }

    @Override
    public Mono<Producto> actualizarNombre(Long productoId, String nuevoNombre) {
        return productoRepositoryPort.findById(productoId)
                .switchIfEmpty(Mono.error(new RuntimeException("Producto no encontrado con id: " + productoId)))
                .flatMap(p -> {
                    p.setNombre(nuevoNombre);
                    return productoRepositoryPort.save(p);
                });
    }

    @Override
    public Mono<Void> eliminar(Long productoId) {
        return productoRepositoryPort.deleteById(productoId);
    }

    @Override
    public Mono<Producto> actualizarStock(Long productoId, int nuevoStock) {
        return productoRepositoryPort.findById(productoId)
                .switchIfEmpty(Mono.error(new RuntimeException("Producto no encontrado con id: " + productoId)))
                .flatMap(p -> {
                    p.setStock(nuevoStock);
                    return productoRepositoryPort.save(p);
                });
    }

    @Override
    public Flux<Producto> productoConMasStockPorSucursal(Long franquiciaId) {
        return sucursalRepositoryPort.findByFranquiciaId(franquiciaId)
                .flatMap(s -> 
                    productoRepositoryPort.findBySucursalId(s.getId())
                        .reduce((p1, p2) -> p1.getStock() >= p2.getStock() ? p1 : p2)
                );
    }

    
}
