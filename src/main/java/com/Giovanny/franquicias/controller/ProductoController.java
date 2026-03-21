package com.Giovanny.franquicias.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.Giovanny.franquicias.model.Producto;
import com.Giovanny.franquicias.service.ProductoService;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping("/sucursal/{sucursalId}")
    public ResponseEntity<Producto> agregar(@PathVariable Long sucursalId, @RequestBody Producto producto) {
        // Aquí se llamaría al servicio para agregar el producto a la sucursal
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.agregar(sucursalId, producto));
    }
    
    @PutMapping("/{id}/nombre")
    public ResponseEntity<Producto> actualizarNombre(@PathVariable Long id, @RequestParam String nombre) {
        // Aquí se llamaría al servicio para actualizar el nombre del producto
        return ResponseEntity.ok(productoService.actualizarNombre(id, nombre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        // Aquí se llamaría al servicio para eliminar el producto
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<Producto> actualizarStock(@PathVariable Long id, @RequestParam Integer stock) {
        // Aquí se llamaría al servicio para actualizar el stock del producto
        return ResponseEntity.ok(productoService.actualizarStock(id, stock));
    }

    @GetMapping("/franquicia/{franquiciaId}/mayor-stock")
    public ResponseEntity<List<Producto>> productoConMasStockPorSucursal(@PathVariable Long franquiciaId) {
        // Aquí se llamaría al servicio para obtener el producto con más stock por sucursal de la franquicia
        return ResponseEntity.ok(productoService.productoConMasStockPorSucursal(franquiciaId));
    }
}
