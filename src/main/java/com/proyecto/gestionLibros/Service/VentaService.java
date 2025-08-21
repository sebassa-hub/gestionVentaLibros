package com.proyecto.gestionLibros.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.gestionLibros.entity.Venta;
import com.proyecto.gestionLibros.repository.VentaRepository;

@Service
public class VentaService {

	@Autowired
	private VentaRepository ventaRepository;
	
	
	public List<Venta> listarTodos(){
		return ventaRepository.findAll();
	}
	
	
	public Venta guardar(Venta venta) {
		return ventaRepository.save(venta);
	}
	
	public Venta obtenerPorId(Long id) {
		return ventaRepository.findById(id).orElse(null);
	}
	
	public void eliminar(Long id) {
		ventaRepository.deleteById(id);
	}
	
	
}
