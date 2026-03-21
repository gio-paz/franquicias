package com.Giovanny.franquicias.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Giovanny.franquicias.model.Franquicia;
import com.Giovanny.franquicias.model.Sucursal;
import com.Giovanny.franquicias.repository.FranquiciaRepository;
import com.Giovanny.franquicias.repository.SucursalRepository;

import net.bytebuddy.asm.MemberSubstitution.Substitution.Chain.Step;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class SucursalServiceTest {
    
    @Mock
    private SucursalRepository sucursalRepository;

    @Mock
    private FranquiciaRepository franquiciaRepository;

    @InjectMocks
    private SucursalService sucursalService;

    @Test
    void agregar_debeGuardarSucursalCuandoFranquiciaExiste() {
        Franquicia franquicia = new Franquicia();
        franquicia.setId(1L);

        Sucursal sucursal = new Sucursal();
        sucursal.setNombre("Sucursal Bogota");

        when(franquiciaRepository.findById(1L)).thenReturn(Mono.just(franquicia));
        when(sucursalRepository.save(any())).thenAnswer(inv -> Mono.just(inv.getArgument(0)));

        StepVerifier.create(sucursalService.agregar(1L, sucursal))
                .expectNextMatches(s -> s.getNombre().equals("Sucursal Bogota") && s.getFranquiciaId().equals(1L))
                .verifyComplete();
    }

    @Test
    void agregar_debeRetornarErrorCuandoFranquiciaNoExiste() {
        when(franquiciaRepository.findById(99L)).thenReturn(Mono.empty());

        StepVerifier.create(sucursalService.agregar(99L, new Sucursal()))
                .expectErrorMessage("Franquicia no encontrada con id: 99")
                .verify();
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
