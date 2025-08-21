package com.proyecto.gestionLibros.service;

import com.proyecto.gestionLibros.entity.Cliente;
import com.proyecto.gestionLibros.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteService;
	
	public List<Cliente> listarTodos(){
		return clienteService.findAll();
	}
	
	
	
}
