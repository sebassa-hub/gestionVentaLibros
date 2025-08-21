package com.proyecto.gestionLibros.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.gestionLibros.entity.Editorial;
import com.proyecto.gestionLibros.repository.EditorialRepository;

@Service
public class EditorialService {
	
	@Autowired
	private EditorialRepository editorialRepo;
	
	public List<Editorial> listar(){
		return editorialRepo.findAll();
	}
	
	public Editorial guardar(Editorial editorial) {
		return editorialRepo.save(editorial);
	}
	
	public Editorial obtenerPorId(Long id) {
		return editorialRepo.findById(id).orElse(null);
	}
	
	public Editorial actualizar(Editorial editorial) {
		return editorialRepo.save(editorial);
	}
}