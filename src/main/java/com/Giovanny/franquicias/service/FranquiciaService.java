package com.Giovanny.franquicias.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.Giovanny.franquicias.model.Franquicia;
import com.Giovanny.franquicias.repository.FranquiciaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FranquiciaService {
    
    private final FranquiciaRepository franquiciaRepository;

    public Franquicia agregar(Franquicia franquicia) {
        return franquiciaRepository.save(franquicia);
    }   

    public Franquicia actualizarNombre(Long id, String nuevoNombre) {
        Franquicia franquicia = franquiciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Franquicia no encontrada con id: " + id));
        franquicia.setNombre(nuevoNombre);
        return franquiciaRepository.save(franquicia);
    }

    public List<Franquicia> listarTodas() {
        return franquiciaRepository.findAll();
    }
}
