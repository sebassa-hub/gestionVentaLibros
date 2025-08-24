package com.proyecto.gestionLibros.service;

import java.util.List;

import com.libro.demo.entity.Usuario;

public interface UsuarioService {
		public Usuario guardarUsuario(Usuario registroDTO);
		
		public List<Usuario> listarTodosUsuario();
		
		public boolean login(Usuario usuario);
		
		public Usuario buscarByUsuario(String username);
		
		List<Usuario> listarAsesores();

		public Usuario findByUsername(String username);
}
