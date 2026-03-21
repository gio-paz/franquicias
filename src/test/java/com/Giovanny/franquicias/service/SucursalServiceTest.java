package com.Giovanny.franquicias.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Giovanny.franquicias.domain.model.Sucursal;
import com.Giovanny.franquicias.domain.port.out.SucursalRepositoryPort;
import com.Giovanny.franquicias.application.usecase.SucursalUseCaseImpl;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class SucursalServiceTest {
    
    @Mock
    private SucursalRepositoryPort sucursalRepository;

    @InjectMocks
    private SucursalUseCaseImpl sucursalService;

    @Test
    void agregar_debeGuardarSucursalConFranquiciaId() {
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre("Sucursal Bogota");

        when(sucursalRepository.save(any())).thenAnswer(inv -> Mono.just(inv.getArgument(0)));

        StepVerifier.create(sucursalService.agregar(1L, sucursal))
                .expectNextMatches(s -> s.getNombre().equals("Sucursal Bogota") && s.getFranquiciaId().equals(1L))
                .verifyComplete();
    }

    @Test
    void actualizarNombre_debeActualizarCuandoExiste() {
        Sucursal sucursal = new Sucursal();
        sucursal.setId(1L);
        sucursal.setNombre("Sucursal Original");

        when(sucursalRepository.findById(1L)).thenReturn(Mono.just(sucursal));
        when(sucursalRepository.save(any())).thenAnswer(inv -> Mono.just(inv.getArgument(0)));

        StepVerifier.create(sucursalService.actualizarNombre(1L, "Sucursal Actualizada"))
                .expectNextMatches(s -> s.getNombre().equals("Sucursal Actualizada"))
                .verifyComplete();
    }

    @Test
    void actualizarNombre_debeRetornarErrorCuandoNoExiste() {
        when(sucursalRepository.findById(99L)).thenReturn(Mono.empty());

        StepVerifier.create(sucursalService.actualizarNombre(99L, "Sucursal Actualizada"))
                .expectErrorMessage("Sucursal no encontrada con id: 99")
                .verify();
    }
}
