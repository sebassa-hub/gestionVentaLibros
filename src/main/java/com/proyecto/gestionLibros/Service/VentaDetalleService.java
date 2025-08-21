package com.proyecto.gestionLibros.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.gestionLibros.entity.VentaDetalle;
import com.proyecto.gestionLibros.repository.VentaDetalleRepository;

@Service
public class VentaDetalleService {

	@Autowired
	private VentaDetalleRepository ventaDetalleRepository;
	
	public List<VentaDetalle> listarTodos(){
		return ventaDetalleRepository.findAll();
	}
	
	public VentaDetalle guardar(VentaDetalle ventaDetalle) {
		return ventaDetalleRepository.save(ventaDetalle);
	}	
	
	
	public VentaDetalle obtenerPorId(Long id) {
		return ventaDetalleRepository.findById(id).orElse(null);
	}
	
	public void eliminar(Long id) {
		ventaDetalleRepository.deleteById(id);
	}
	
}
