package com.Giovanny.franquicias.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "producto")

public class Producto {
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false)
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "sucursal_id", nullable = false)
    private Sucursal sucursal;
}
