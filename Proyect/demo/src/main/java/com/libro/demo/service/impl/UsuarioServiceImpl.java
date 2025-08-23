package com.libro.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.libro.demo.entity.Rol;
import com.libro.demo.entity.Usuario;
import com.libro.demo.repository.UsuarioRepository;
import com.libro.demo.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	private UsuarioRepository usuarioRepositorio;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepositorio) {
		super();
		this.usuarioRepositorio = usuarioRepositorio;
	}
	
	@Override
	public Usuario guardarUsuario(Usuario objUsuario) {
		Usuario usuario = new Usuario(objUsuario.getNombres(),objUsuario.getApellidos(),
				objUsuario.getUsername(),objUsuario.getClave(), objUsuario.getRol()) ;
		
				
		return usuarioRepositorio.save(usuario);
	}

	@Override
	public List<Usuario> listarTodosUsuario() {
		
		return usuarioRepositorio.findAll();
	}

	@Override
	public boolean login(Usuario usuario) {
		boolean band;
		// TODO Auto-generated method stub
		
				Usuario entidad=usuarioRepositorio.findByUsuarioAndClave(usuario.getUsername(), usuario.getClave());
				System.out.println("usuario.getUsername()--> "+usuario.getUsername());
				System.out.println("usuario.getClave()--> "+usuario.getClave());
				
				if(entidad==null) {
					band=false;
				}else {
					band=true;
				}
				
				return band;
	}

	@Override
	public Usuario buscarByUsuario(String username) {
		// TODO Auto-generated method stub
		Usuario entidad=usuarioRepositorio.findByUsuario(username);
		
		return entidad;
	}

	@Override
	public List<Usuario> listarAsesores() {
		
		return usuarioRepositorio.findUsuariosByRolAsesor();
	}
}
