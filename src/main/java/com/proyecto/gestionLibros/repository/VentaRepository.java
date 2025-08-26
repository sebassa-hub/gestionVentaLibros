package com.proyecto.gestionLibros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.gestionLibros.entity.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long>{
    List<Venta> findByCliente_Id(Long id);
}
