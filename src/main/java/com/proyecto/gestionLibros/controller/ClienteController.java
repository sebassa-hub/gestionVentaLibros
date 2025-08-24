package com.proyecto.gestionLibros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.proyecto.gestionLibros.Service.ClienteService;
import com.proyecto.gestionLibros.entity.Cliente;

@CrossOrigin(origins = "http://localhost:4200") // Permite llamadas desde Angular
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Listar todos los clientes
    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarTodos();
    }

    // Crear un nuevo cliente
    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteService.guardar(cliente);
    }

    // Obtener un cliente por ID
    @GetMapping("/{id}")
    public Cliente obtenerCliente(@PathVariable Long id) {
        return clienteService.obtenerPorId(id);
    }

    // Actualizar un cliente por ID
    @PutMapping("/{id}")
    public Cliente actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente existente = clienteService.obtenerPorId(id);
        if (existente != null) {
            existente.setNombres(cliente.getNombres());
            existente.setApellidos(cliente.getApellidos());
            existente.setTipoDocumento_Cliente(cliente.getTipoDocumento_Cliente());
            existente.setNumeroDocumento(cliente.getNumeroDocumento());
            existente.setTelefono(cliente.getTelefono());
            existente.setTipoGenero_Cliente(cliente.getTipoGenero_Cliente());
            existente.setFechaNacimiento(cliente.getFechaNacimiento());
            existente.setCorreo(cliente.getCorreo());
            return clienteService.guardar(existente);
        } else {
            // Puedes lanzar una excepción o devolver null según tu manejo de errores
            return null;
        }
    }

    // Eliminar un cliente por ID
    @DeleteMapping("/{id}")
    public void eliminarCliente(@PathVariable Long id) {
        clienteService.eliminar(id);
    }
}
