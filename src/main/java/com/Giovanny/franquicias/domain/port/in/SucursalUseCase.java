package com.Giovanny.franquicias.domain.port.in;

import com.Giovanny.franquicias.domain.model.Sucursal;
import reactor.core.publisher.Mono;

public interface SucursalUseCase {
    
    Mono<Sucursal> agregar(Long franquiciaId, Sucursal sucursal);
    Mono<Sucursal> actualizarNombre(Long id, String nuevoNombre);

}
