package com.libro.demo.service;

import java.util.List;

import com.libro.demo.entity.Rol;

public interface RolService {
	
	public List<Rol> listarTodosRol();
	
	public Rol buscarById(Integer id);

}
