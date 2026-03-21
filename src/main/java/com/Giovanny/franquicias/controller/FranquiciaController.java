package com.Giovanny.franquicias.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import com.Giovanny.franquicias.model.Franquicia;
import com.Giovanny.franquicias.service.FranquiciaService;

import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/api/franquicias")
@RequiredArgsConstructor
public class FranquiciaController {

    private final FranquiciaService franquiciaService;

    @PostMapping
    public ResponseEntity<Franquicia> agregar(@RequestBody Franquicia franquicia) {
        // Aquí se llamaría al servicio para agregar la franquicia
        return ResponseEntity.status(HttpStatus.CREATED).body(franquiciaService.agregar(franquicia)); 
    }

    @PutMapping("/{id}/nombre")
    public ResponseEntity<Franquicia> actualizarNombre(@PathVariable Long id, @RequestParam String nombre) {
        // Aquí se llamaría al servicio para actualizar el nombre de la franquicia
        return ResponseEntity.ok(franquiciaService.actualizarNombre(id, nombre));
    }

    @GetMapping
    public ResponseEntity<List<Franquicia>> listarTodas() {
        // Aquí se llamaría al servicio para listar todas las franquicias
        return ResponseEntity.ok(franquiciaService.listarTodas());
    }
    
}
