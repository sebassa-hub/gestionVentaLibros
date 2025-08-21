package com.proyecto.gestionLibros.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.gestionLibros.entity.Categoria;
import com.proyecto.gestionLibros.repository.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	public List<Categoria> listar(){
		return categoriaRepo.findAll();
	}
	
	public Categoria guardar(Categoria categoria) {
		return categoriaRepo.save(categoria);
	}
	
	public Categoria obtenerPorId(Long id) {
		return categoriaRepo.findById(id).orElse(null);
	}
	
	public Categoria actualizar(Categoria categoria) {
		return categoriaRepo.save(categoria);
	}
}
