package com.Giovanny.franquicias.service;

import com.Giovanny.franquicias.model.Sucursal;
import com.Giovanny.franquicias.repository.FranquiciaRepository;
import com.Giovanny.franquicias.repository.SucursalRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SucursalService {

    private final SucursalRepository sucursalRepository;
    private final FranquiciaRepository franquiciaRepository;

    public Mono<Sucursal> agregar(Long franquiciaId, Sucursal sucursal) {
        return franquiciaRepository.findById(franquiciaId)
                .switchIfEmpty(Mono.error(new RuntimeException("Franquicia no encontrada con id: " + franquiciaId)))
                .flatMap(franquicia -> {
                    sucursal.setFranquiciaId(franquiciaId);
                    return sucursalRepository.save(sucursal);
                });
    }

    public Mono<Sucursal> actualizarNombre(Long id, String nuevoNombre) {
        return sucursalRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Sucursal no encontrada con id: " + id)))
                .flatMap(sucursal -> {
                    sucursal.setNombre(nuevoNombre);
                    return sucursalRepository.save(sucursal);
                });
    }
    
}
