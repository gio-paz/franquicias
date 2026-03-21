package com.Giovanny.franquicias.application.usecase;

import com.Giovanny.franquicias.domain.model.Sucursal;
import com.Giovanny.franquicias.domain.port.in.SucursalUseCase;
import com.Giovanny.franquicias.domain.port.out.SucursalRepositoryPort;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SucursalUseCaseImpl implements SucursalUseCase {

    private final SucursalRepositoryPort sucursalRepositoryPort;

    @Override
    public Mono<Sucursal> agregar(Long franquiciaId, Sucursal sucursal) {
        sucursal.setFranquiciaId(franquiciaId);
        return sucursalRepositoryPort.save(sucursal);
    }


    @Override
    public Mono<Sucursal> actualizarNombre(Long id, String nuevoNombre) {   
        return sucursalRepositoryPort.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Sucursal no encontrada con id: " + id)))
                .flatMap(s -> {
                    s.setNombre(nuevoNombre);
                    return sucursalRepositoryPort.save(s);
                });
    }
    
}
