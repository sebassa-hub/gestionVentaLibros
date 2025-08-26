package com.proyecto.gestionLibros.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.gestionLibros.entity.Libro;
import com.proyecto.gestionLibros.repository.LibroRepository;

@Service
public class LibroService {
	
	@Autowired
	private LibroRepository libroRepo;
	
	//listar los libros
	public List<Libro> listarLibro(){
		return libroRepo.findAll();
	}
	
	//guardar los libros
	public Libro guardar(Libro libro) {
	   if (libro.getAutor() == null || libro.getAutor().isBlank()) {
       libro.setAutor("Desconocido");
	}

		return libroRepo.save(libro);
	}
	
	//Obtener libro por Id
	public Libro obternerporId(Long id) {
		return libroRepo.findById(id).orElse(null);
	}
	
	//Actualizar
	public Libro actualizar(Libro libro) {
		return libroRepo.save(libro);
	}

	public void eliminar(Long id) {
		libroRepo.deleteById(id);
	}
}
