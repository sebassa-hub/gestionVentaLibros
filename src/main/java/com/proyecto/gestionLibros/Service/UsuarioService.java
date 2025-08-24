package com.proyecto.gestionLibros.Service;

import java.util.List;

import com.proyecto.gestionLibros.entity.Usuario;

public interface UsuarioService {
		public Usuario guardarUsuario(Usuario registroDTO);
		
		public List<Usuario> listarTodosUsuario();
		
		public boolean login(Usuario usuario);
		
		public Usuario buscarByUsuario(String username);
		
		List<Usuario> listarAsesores();

		public Usuario findByUsername(String username);
}
