package com.proyecto.gestionLibros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.gestionLibros.entity.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long>{

	
	
	
}
