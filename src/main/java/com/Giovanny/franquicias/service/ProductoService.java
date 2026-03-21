package com.Giovanny.franquicias.service;

import org.springframework.stereotype.Service;
import com.Giovanny.franquicias.model.Producto;
import com.Giovanny.franquicias.repository.ProductoRepository;
import com.Giovanny.franquicias.repository.SucursalRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductoService {
    
    private final ProductoRepository productoRepository;
    private final SucursalRepository sucursalRepository;

    public Mono<Producto> agregar(Long sucursalId, Producto producto) {
        return sucursalRepository.findById(sucursalId)
                .switchIfEmpty(Mono.error(new RuntimeException("Sucursal no encontrada con id: " + sucursalId)))
                .flatMap(sucursal -> {
                    producto.setSucursalId(sucursalId);
                    return productoRepository.save(producto);
                });
    }

    public Mono<Producto> actualizarNombre(Long productoId, String nuevoNombre) {
        return productoRepository.findById(productoId)
                .switchIfEmpty(Mono.error(new RuntimeException("Producto no encontrado con id: " + productoId)))
                .flatMap(producto -> {
                    producto.setNombre(nuevoNombre);
                    return productoRepository.save(producto);
                });
    }

    public Mono<Void> eliminar(Long productoId) {
        return productoRepository.findById(productoId)
                .switchIfEmpty(Mono.error(new RuntimeException("Producto no encontrado con id: " + productoId)))
                .flatMap(producto -> productoRepository.deleteById(productoId));
    }

    public Mono<Producto> actualizarStock(Long productoId, int nuevoStock) {
        return productoRepository.findById(productoId)
                .switchIfEmpty(Mono.error(new RuntimeException("Producto no encontrado con id: " + productoId)))
                .flatMap(producto -> {
                    producto.setStock(nuevoStock);
                    return productoRepository.save(producto);
                });
    }

    public Flux<Producto> productoConMasStockPorSucursal(Long franquiciaId) {
        return sucursalRepository.findByFranquiciaId(franquiciaId)
                .flatMap(sucursal -> 
                    productoRepository.findBySucursalId(sucursal.getId())
                        .reduce((p1, p2) -> p1.getStock() >= p2.getStock() ? p1 : p2)
                );
    }
}
