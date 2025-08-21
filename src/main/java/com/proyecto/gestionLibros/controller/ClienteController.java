package com.proyecto.gestionLibros.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.gestionLibros.Service.ClienteService;
import com.proyecto.gestionLibros.entity.Cliente;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public List<Cliente> listarClientes(){	
		return clienteService.listarTodos();
	}
	
	@PostMapping
	public Cliente crearCliente(@RequestBody Cliente cliente) {
		return clienteService.guardar(cliente);
	}
	
	@GetMapping("/{id}")
	public Cliente obtenerCliente(@PathVariable Long id) {
		return clienteService.obtenerPorId(id);
	}
	
	@DeleteMapping("/{id}")
	public void eliminarCliente(@PathVariable Long id) {
		clienteService.eliminar(id);
	}
	
	
	
}
