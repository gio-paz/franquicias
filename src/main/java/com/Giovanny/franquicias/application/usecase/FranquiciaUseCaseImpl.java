package com.Giovanny.franquicias.application.usecase;

import com.Giovanny.franquicias.domain.model.Franquicia;
import com.Giovanny.franquicias.domain.port.in.FranquiciaUseCase;
import com.Giovanny.franquicias.domain.port.out.FranquiciaRepositoryPort;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FranquiciaUseCaseImpl implements FranquiciaUseCase {

    private final FranquiciaRepositoryPort franquiciaRepositoryPort;

    @Override
    public Mono<Franquicia> agregar(Franquicia franquicia) {
        return franquiciaRepositoryPort.save(franquicia);
    }

    @Override
    public Mono<Franquicia> actualizarNombre(Long id, String nuevoNombre) {
        return franquiciaRepositoryPort.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Franquicia no encontrada con id: " + id)))
                .flatMap(f -> {
                    f.setNombre(nuevoNombre);
                    return franquiciaRepositoryPort.save(f);
                });
    }

    @Override
    public Flux<Franquicia> listarTodas() {
        return franquiciaRepositoryPort.findAll();
    }
    
}
