package com.Giovanny.franquicias.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("producto")

public class Producto {
     
    @Id
    private Long id;

    private String nombre;

    private Integer stock;

    private Long sucursalId;
}
