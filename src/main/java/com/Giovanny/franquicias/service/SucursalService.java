package com.Giovanny.franquicias.service;

import com.Giovanny.franquicias.model.Franquicia;
import com.Giovanny.franquicias.model.Sucursal;
import com.Giovanny.franquicias.repository.FranquiciaRepository;
import com.Giovanny.franquicias.repository.SucursalRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SucursalService {

    private final SucursalRepository sucursalRepository;
    private final FranquiciaRepository franquiciaRepository;

    public Sucursal agregar(Long franquiciaId, Sucursal sucursal) {
        Franquicia franquicia = franquiciaRepository.findById(franquiciaId)
                .orElseThrow(() -> new RuntimeException("Franquicia no encontrada con id: " + franquiciaId));
        sucursal.setFranquicia(franquicia);
        return sucursalRepository.save(sucursal);
    }

    public Sucursal actualizarNombre(Long id, String nuevoNombre) {
        Sucursal sucursal = sucursalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada con id: " + id));
        sucursal.setNombre(nuevoNombre);
        return sucursalRepository.save(sucursal);
    }
    
}
