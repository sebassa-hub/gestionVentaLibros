package com.proyecto.gestionLibros.service;

import com.proyecto.gestionLibros.entity.Cliente;
import com.proyecto.gestionLibros.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	
	public List<Cliente> listarTodos(){
		return clienteRepository.findAll();
	}
	
	public Cliente guardar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	
	public Cliente obtenerPorId(Long id) {
		return clienteRepository.findById(id).orElse(null);
	}
	
	
	public void eliminar(Long id) {
		clienteRepository.deleteById(id);
	}
	
}
