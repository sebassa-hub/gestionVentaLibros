package com.proyecto.gestionLibros.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.gestionLibros.entity.Rol;
import com.proyecto.gestionLibros.repository.RolRepository;
import com.proyecto.gestionLibros.Service.RolService;

@Service
public class RolServiceImpl implements RolService{
	@Autowired
	RolRepository rolRepository;

	@Override
	public List<Rol> listarTodosRol() {
		return rolRepository.findAll();
	}

	@Override
	public Rol buscarById(Integer id) {
		return rolRepository.findById(id).get();
	}

}
