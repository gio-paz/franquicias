package com.Giovanny.franquicias.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Giovanny.franquicias.model.Sucursal;
import com.Giovanny.franquicias.service.SucursalService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/sucursales")
@RequiredArgsConstructor
public class SucursalController {

    private final SucursalService sucursalService;

    @PostMapping("/franquicia/{franquiciaId}")
    public Mono<ResponseEntity<Sucursal>> agregar(@PathVariable Long franquiciaId, @RequestBody Sucursal sucursal) {
        // Aquí se llamaría al servicio para agregar la sucursal a la franquicia correspondiente
        return sucursalService.agregar(franquiciaId, sucursal)
                .map(s -> ResponseEntity.status(HttpStatus.CREATED).body(s));
    }

    @PutMapping("/{id}/nombre")
    public Mono<ResponseEntity<Sucursal>> actualizarNombre(@PathVariable Long id, @RequestBody String nombre) {
        // Aquí se llamaría al servicio para actualizar el nombre de la sucursal
        return sucursalService.actualizarNombre(id, nombre)
                .map(ResponseEntity::ok);
    }
}
