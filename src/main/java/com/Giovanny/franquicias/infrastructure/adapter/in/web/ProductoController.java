package com.Giovanny.franquicias.infrastructure.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Giovanny.franquicias.domain.model.Producto;
import com.Giovanny.franquicias.domain.port.in.ProductoUseCase;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor

public class ProductoController {
    
    private final ProductoUseCase productoUseCase;

    @PostMapping("/sucursal/{sucursalId}")
    public Mono<ResponseEntity<Producto>> agregar(@PathVariable Long sucursalId,
                                                   @RequestBody Producto producto) {
        return productoUseCase.agregar(sucursalId, producto)
                .map(p -> ResponseEntity.status(HttpStatus.CREATED).body(p));
    }

    @PutMapping("/{id}/nombre")
    public Mono<ResponseEntity<Producto>> actualizarNombre(@PathVariable Long id,
                                                            @RequestParam String nuevoNombre) {
        return productoUseCase.actualizarNombre(id, nuevoNombre)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> eliminar(@PathVariable Long id) {
        return productoUseCase.eliminar(id)
                .thenReturn(ResponseEntity.noContent().<Void>build());
    }

    @PutMapping("/{id}/stock")
    public Mono<ResponseEntity<Producto>> actualizarStock(@PathVariable Long id,
                                                           @RequestParam int nuevoStock) {
        return productoUseCase.actualizarStock(id, nuevoStock)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/franquicia/{franquiciaId}/mayor-stock")
    public Flux<Producto> productoConMasStockPorSucursal(@PathVariable Long franquiciaId) {
        return productoUseCase.productoConMasStockPorSucursal(franquiciaId);
    }

}
