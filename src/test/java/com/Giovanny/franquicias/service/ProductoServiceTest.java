package com.Giovanny.franquicias.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Giovanny.franquicias.model.Producto;
import com.Giovanny.franquicias.model.Sucursal;
import com.Giovanny.franquicias.repository.ProductoRepository;
import com.Giovanny.franquicias.repository.SucursalRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {
    
    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private SucursalRepository sucursalRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    void agregar_debeGuardarProductoCuandoSucursalExiste() {
        Sucursal sucursal = new Sucursal();
        sucursal.setId(1L);

        Producto producto = new Producto();
        producto.setNombre("Producto Test");
        producto.setStock(100);

        when(sucursalRepository.findById(1L)).thenReturn(Mono.just(sucursal));
        when(productoRepository.save(any())).thenAnswer(inv -> Mono.just(inv.getArgument(0)));

        StepVerifier.create(productoService.agregar(1L, producto))
                .expectNextMatches(p -> p.getNombre().equals("Producto Test") && p.getSucursalId().equals(1L) && p.getStock() == 100)
                .verifyComplete();
    }

    @Test
    void agregar_debeRetornarErrorCuandoSucursalNoExiste() {
        when(sucursalRepository.findById(99L)).thenReturn(Mono.empty());

        StepVerifier.create(productoService.agregar(99L, new Producto()))
                .expectErrorMessage("Sucursal no encontrada con id: 99")
                .verify();
    }

    @Test
    void actualizarStock_debeActualizarCuandoExiste() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setStock(10);

        when(productoRepository.findById(1L)).thenReturn(Mono.just(producto));
        when(productoRepository.save(any())).thenAnswer(inv -> Mono.just(inv.getArgument(0)));

        StepVerifier.create(productoService.actualizarStock(1L, 50))
                .expectNextMatches(p -> p.getStock().equals(50))
                .verifyComplete();
    }

    @Test
    void eliminar_debeErrorCuandoNoExiste() {
        when(productoRepository.findById(99L)).thenReturn(Mono.empty());

        StepVerifier.create(productoService.eliminar(99L))
                .expectErrorMessage("Producto no encontrado con id: 99")
                .verify();
    }

    @Test
    void productoConMasStockPorSucursal_debeRetornarElDeMayorStock() {
        Sucursal sucursal = new Sucursal();
        sucursal.setId(1L);

        Producto p1 = new Producto();
        p1.setNombre("producto A");
        p1.setStock(30);
        p1.setSucursalId(1L);

        Producto p2 = new Producto();
        p2.setNombre("producto B");
        p2.setStock(80);
        p2.setSucursalId(1L);

        when(sucursalRepository.findByFranquiciaId(1L)).thenReturn(Flux.just(sucursal));
        when(productoRepository.findBySucursalId(1L)).thenReturn(Flux.just(p1, p2));

        StepVerifier.create(productoService.productoConMasStockPorSucursal(1L))
                .expectNextMatches(p -> p.getNombre().equals("producto B") && p.getStock() == 80)
                .verifyComplete();
    }
}
