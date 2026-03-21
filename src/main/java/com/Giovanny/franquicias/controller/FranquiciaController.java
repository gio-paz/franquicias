package com.Giovanny.franquicias.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import com.Giovanny.franquicias.model.Franquicia;
import com.Giovanny.franquicias.service.FranquiciaService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/franquicias")
@RequiredArgsConstructor
public class FranquiciaController {

    private final FranquiciaService franquiciaService;

    @PostMapping
    public Mono<ResponseEntity<Franquicia>> agregar(@RequestBody Franquicia franquicia) {
        // Aquí se llamaría al servicio para agregar la franquicia
        return franquiciaService.agregar(franquicia)
                .map(f -> ResponseEntity.status(HttpStatus.CREATED).body(f));
    }

    @PutMapping("/{id}/nombre")
    public Mono<ResponseEntity<Franquicia>> actualizarNombre(@PathVariable Long id, @RequestParam String nombre) {
        // Aquí se llamaría al servicio para actualizar el nombre de la franquicia
        return franquiciaService.actualizarNombre(id, nombre)
                .map(ResponseEntity::ok);
    }

    @GetMapping
    public Flux<Franquicia> listarTodas() {
        // Aquí se llamaría al servicio para listar todas las franquicias
        return franquiciaService.listarTodas();
    }
    
}
