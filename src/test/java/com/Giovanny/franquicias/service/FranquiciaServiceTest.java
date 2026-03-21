package com.Giovanny.franquicias.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Giovanny.franquicias.domain.model.Franquicia;
import com.Giovanny.franquicias.domain.port.out.FranquiciaRepositoryPort;
import com.Giovanny.franquicias.application.usecase.FranquiciaUseCaseImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)

public class FranquiciaServiceTest {

    @Mock
    private FranquiciaRepositoryPort franquiciaRepository;

    @InjectMocks
    private FranquiciaUseCaseImpl franquiciaService;

    @Test
    void agregar_debeRetornarFranquiciaGuardada() {
        Franquicia franquicia = new Franquicia();
        franquicia.setNombre("Franquicia Test");

        when(franquiciaRepository.save(any())).thenReturn(Mono.just(franquicia));

        StepVerifier.create(franquiciaService.agregar(franquicia))
                .expectNextMatches(f -> f.getNombre().equals("Franquicia Test"))
                .verifyComplete();
    }

    @Test
    void actualizarNombre_debeActualizarCuandoExiste() {
        Franquicia franquicia = new Franquicia();
        franquicia.setId(1L);
        franquicia.setNombre("Franquicia Original");

        when(franquiciaRepository.findById(1L)).thenReturn(Mono.just(franquicia));
        when(franquiciaRepository.save(any())).thenAnswer(inv -> Mono.just(inv.getArgument(0)));

        StepVerifier.create(franquiciaService.actualizarNombre(1L, "Franquicia Actualizada"))
                .expectNextMatches(f -> f.getNombre().equals("Franquicia Actualizada"))
                .verifyComplete();
    }

    @Test
    void actualizarNombre_debeRetornarErrorCuandoNoExiste() {
        when(franquiciaRepository.findById(99L)).thenReturn(Mono.empty());

        StepVerifier.create(franquiciaService.actualizarNombre(99L, "Franquicia Actualizada"))
                .expectErrorMessage("Franquicia no encontrada con id: 99")
                .verify();
    }

    @Test
    void listarTodas_debeRetornarTodasLasFranquicias() {
        Franquicia f1 = new Franquicia();
        f1.setNombre("Franquicia 1");
        Franquicia f2 = new Franquicia();
        f2.setNombre("Franquicia 2");

        when(franquiciaRepository.findAll()).thenReturn(Flux.just(f1, f2));

        StepVerifier.create(franquiciaService.listarTodas())
                .expectNextCount(2)
                .verifyComplete();
    }
}

