package com.Giovanny.franquicias.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "sucursal")

public class Sucursal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "franquicia_id", nullable = false)
    private Franquicia franquicia;

    @JsonIgnore
    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Producto> productos;
}
