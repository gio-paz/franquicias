package com.Giovanny.franquicias.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.Giovanny.franquicias.model.Producto;
import com.Giovanny.franquicias.service.ProductoService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping("/sucursal/{sucursalId}")
    public Mono<ResponseEntity<Producto>> agregar(@PathVariable Long sucursalId, @RequestBody Producto producto) {
        // Aquí se llamaría al servicio para agregar el producto a la sucursal
        return productoService.agregar(sucursalId, producto)
                .map(p -> ResponseEntity.status(HttpStatus.CREATED).body(p));
    }
    
    @PutMapping("/{id}/nombre")
    public Mono<ResponseEntity<Producto>> actualizarNombre(@PathVariable Long id, @RequestParam String nombre) {
        // Aquí se llamaría al servicio para actualizar el nombre del producto
        return productoService.actualizarNombre(id, nombre)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> eliminar(@PathVariable Long id) {
        // Aquí se llamaría al servicio para eliminar el producto
        return productoService.eliminar(id)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()));
        
    }

    @PutMapping("/{id}/stock")
    public Mono<ResponseEntity<Producto>> actualizarStock(@PathVariable Long id, @RequestParam Integer stock) {
        // Aquí se llamaría al servicio para actualizar el stock del producto
        return productoService.actualizarStock(id, stock)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/franquicia/{franquiciaId}/mayor-stock")
    public Flux<Producto> productoConMasStockPorSucursal(@PathVariable Long franquiciaId) {
        // Aquí se llamaría al servicio para obtener el producto con más stock por sucursal de la franquicia
        return productoService.productoConMasStockPorSucursal(franquiciaId);
    }
}
