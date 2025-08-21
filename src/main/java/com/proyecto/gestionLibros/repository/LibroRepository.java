package com.proyecto.gestionLibros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto.gestionLibros.entity.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long>{
	@Query(value = "SELECT id_libro FROM Libro ORDER BY id_libro DESC LIMIT 1", nativeQuery = true)
    int obtenerUltimoId();
}
