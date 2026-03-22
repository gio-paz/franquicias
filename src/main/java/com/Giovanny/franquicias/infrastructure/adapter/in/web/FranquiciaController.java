package com.Giovanny.franquicias.infrastructure.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Giovanny.franquicias.domain.model.Franquicia;
import com.Giovanny.franquicias.domain.port.in.FranquiciaUseCase;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/franquicias")
@RequiredArgsConstructor

public class FranquiciaController {

    private final FranquiciaUseCase franquiciaUseCase;

    @PostMapping
    public Mono<ResponseEntity<Franquicia>> agregar(@RequestBody Franquicia franquicia) {
        return franquiciaUseCase.agregar(franquicia)
                .map(f -> ResponseEntity.status(HttpStatus.CREATED).body(f));
    }

    @PutMapping("/{id}/nombre")
    public Mono<ResponseEntity<Franquicia>> actualizarNombre(@PathVariable Long id, @RequestParam String nuevoNombre) {
        return franquiciaUseCase.actualizarNombre(id, nuevoNombre)
                .map(ResponseEntity::ok);
    }

    @GetMapping
    public Flux<Franquicia> listarTodas() {
        return franquiciaUseCase.listarTodas();
    }
    
}
