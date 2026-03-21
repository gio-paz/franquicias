package com.Giovanny.franquicias.domain.port.in;

import com.Giovanny.franquicias.domain.model.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductoUseCase {
    
    Mono<Producto> agregar(Long sucursalId, Producto producto);
    Mono<Producto> actualizarNombre(Long productoId, String nuevoNombre);
    Mono<Void> eliminar(Long productoId);
    Mono<Producto> actualizarStock(Long productoId, int nuevoStock);
    Flux<Producto> productoConMasStockPorSucursal(Long franquiciaId);

}
