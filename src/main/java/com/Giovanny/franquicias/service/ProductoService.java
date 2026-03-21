package com.Giovanny.franquicias.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

import org.springframework.stereotype.Service;

import com.Giovanny.franquicias.model.Producto;
import com.Giovanny.franquicias.model.Sucursal;
import com.Giovanny.franquicias.repository.ProductoRepository;
import com.Giovanny.franquicias.repository.SucursalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoService {
    
    private final ProductoRepository productoRepository;
    private final SucursalRepository sucursalRepository;

    public Producto agregar(Long sucursalId, Producto producto) {
        Sucursal sucursal = sucursalRepository.findById(sucursalId)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada con id: " + sucursalId));
        producto.setSucursal(sucursal);
        return productoRepository.save(producto);
    }

    public Producto actualizarNombre(Long productoId, String nuevoNombre) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + productoId));
        producto.setNombre(nuevoNombre);
        return productoRepository.save(producto);
    }

    public void eliminar(Long productoId) {
        productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + productoId));
        productoRepository.deleteById(productoId);
    }

    public Producto actualizarStock(Long productoId, int nuevoStock) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + productoId));
        producto.setStock(nuevoStock);
        return productoRepository.save(producto);
    }

    public List<Producto> productoConMasStockPorSucursal(Long franquiciaId) {
        List<Producto> todosLosProductos = productoRepository.findAll();

        return todosLosProductos.stream()
                .filter(p -> p.getSucursal().getFranquicia().getId().equals(franquiciaId))
                .collect(Collectors.groupingBy(
                        p -> p.getSucursal().getId(),
                        Collectors.maxBy(Comparator.comparingInt(Producto::getStock))
                ))
                .values()
                .stream()
                .filter(opt -> opt.isPresent())
                .map(opt -> opt.get())
                .collect(Collectors.toList());
    }
}
