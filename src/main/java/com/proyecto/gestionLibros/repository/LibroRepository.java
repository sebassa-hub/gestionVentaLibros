package com.proyecto.gestionLibros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto.gestionLibros.entity.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long>{

	//@Query(value="SELECT l FROM Libro l where l.categoria.id_categoria =: id_categoria and ")
}
