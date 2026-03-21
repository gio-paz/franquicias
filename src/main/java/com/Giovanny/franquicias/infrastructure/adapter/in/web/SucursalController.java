package com.Giovanny.franquicias.infrastructure.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Giovanny.franquicias.domain.model.Sucursal;
import com.Giovanny.franquicias.domain.port.in.SucursalUseCase;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/sucursales")
@RequiredArgsConstructor

public class SucursalController {

    private final SucursalUseCase sucursalUseCase;

    @PostMapping("/franquicia/{franquiciaId}")
    public Mono<ResponseEntity<Sucursal>> agregar(@PathVariable Long franquiciaId,
                                                   @RequestBody Sucursal sucursal) {
        return sucursalUseCase.agregar(franquiciaId, sucursal)
                .map(s -> ResponseEntity.status(HttpStatus.CREATED).body(s));
    }

    @PutMapping("/{id}/nombre")
    public Mono<ResponseEntity<Sucursal>> actualizarNombre(@PathVariable Long id,
                                                            @RequestParam String nuevoNombre) {
        return sucursalUseCase.actualizarNombre(id, nuevoNombre)
                .map(ResponseEntity::ok);
    }
    
}
