package com.proyecto.gestionLibros.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libro.demo.entity.Rol;
import com.libro.demo.repository.RolRepository;
import com.libro.demo.service.RolService;

@Service
public class RolServiceImpl implements RolService{
	@Autowired
	RolRepository rolRepository;

	@Override
	public List<Rol> listarTodosRol() {
		// TODO Auto-generated method stub
		return rolRepository.findAll();
	}

	@Override
	public Rol buscarById(Integer id) {
		// TODO Auto-generated method stub
		return rolRepository.findById(id).get();
	}

}
