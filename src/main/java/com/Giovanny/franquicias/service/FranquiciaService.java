package com.Giovanny.franquicias.service;

import org.springframework.stereotype.Service;
import com.Giovanny.franquicias.model.Franquicia;
import com.Giovanny.franquicias.repository.FranquiciaRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FranquiciaService {
    
    private final FranquiciaRepository franquiciaRepository;

    public Mono<Franquicia> agregar(Franquicia franquicia) {
        return franquiciaRepository.save(franquicia);
    }   

    public Mono<Franquicia> actualizarNombre(Long id, String nuevoNombre) {
        return franquiciaRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Franquicia no encontrada con id: " + id)))
                .flatMap(franquicia -> {
                    franquicia.setNombre(nuevoNombre);
                    return franquiciaRepository.save(franquicia);
                });
    }

    public Flux<Franquicia> listarTodas() {
        return franquiciaRepository.findAll();
    }
}
