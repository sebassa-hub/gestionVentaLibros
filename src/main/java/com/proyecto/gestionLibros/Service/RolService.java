package com.proyecto.gestionLibros.Service;

import java.util.List;

import com.proyecto.gestionLibros.entity.Rol;

public interface RolService {
	
	public List<Rol> listarTodosRol();
	
	public Rol buscarById(Integer id);

}
