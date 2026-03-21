package com.Giovanny.franquicias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Giovanny.franquicias.model.Sucursal;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
}