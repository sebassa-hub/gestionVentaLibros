package com.proyecto.gestionLibros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.gestionLibros.entity.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long>{

	@Query(value="SELECT l FROM Libro l where l.id_libro = :nLibro", nativeQuery = true)
	Libro buscarPorId(@Param("nLibro")Long id_libro);
}
