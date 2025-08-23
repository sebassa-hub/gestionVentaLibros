package com.proyecto.gestionLibros.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.gestionLibros.dto.VentaRequest;
import com.proyecto.gestionLibros.entity.Cliente;
import com.proyecto.gestionLibros.entity.Libro;
import com.proyecto.gestionLibros.entity.Venta;
import com.proyecto.gestionLibros.entity.VentaDetalle;
import com.proyecto.gestionLibros.repository.ClienteRepository;
import com.proyecto.gestionLibros.repository.LibroRepository;
import com.proyecto.gestionLibros.repository.VentaDetalleRepository;
import com.proyecto.gestionLibros.repository.VentaRepository;

@Service
public class VentaService {

	@Autowired
	private VentaRepository ventaRepository;
	
	@Autowired
	private VentaDetalleRepository detalleRepository;
	
	@Autowired
	private LibroRepository libroRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public List<Venta> listarTodos(){
		return ventaRepository.findAll();
	}
	
	/*
	public Venta guardar(Venta venta) {
		return ventaRepository.save(venta);
	}
	*/
	
	
	public Venta obtenerPorId(Long id) {
		return ventaRepository.findById(id).orElse(null);
	}
	
	public void eliminar(Long id) {
		ventaRepository.deleteById(id);
	}
	
	
	public Venta comprar(VentaRequest request) {

	    Cliente cliente = clienteRepository.findById(request.getClienteId())
	            .orElseThrow(() -> new RuntimeException("Cliente No Encontrado"));

	    Venta venta = new Venta();
	    venta.setCliente(cliente);
	    venta.setFechaVenta(LocalDateTime.now());
	    venta = ventaRepository.save(venta);

	    BigDecimal subtotal = BigDecimal.ZERO;

	    for (VentaRequest.LibroCompra libroCompra : request.getLibros()) {
	        Libro libro = libroRepository.findById(libroCompra.getId_libro())
	                .orElseThrow(() -> new RuntimeException("Libro no encontrado: " + libroCompra.getId_libro()));

	        if (libro.getStock() < libroCompra.getCantidad()) {
	            throw new RuntimeException("Stock insuficiente para el libro: " + libro.getTitulo());
	        }

	        VentaDetalle detalle = new VentaDetalle();
	        detalle.setLibro(libro);
	        detalle.setVenta(venta);
	        detalle.setCantidad(libroCompra.getCantidad());
	        detalle.setPrecioUnitario(libro.getPrecio());
	        detalle.setSubtotal(libro.getPrecio().multiply(BigDecimal.valueOf(libroCompra.getCantidad())));
	        detalleRepository.save(detalle);

	        // Actualizar stock
	        libro.setStock(libro.getStock() - libroCompra.getCantidad());
	        libroRepository.save(libro);

	        subtotal = subtotal.add(detalle.getSubtotal());
	    }

	    // Calcular IGV y total
	    BigDecimal igv = subtotal.multiply(BigDecimal.valueOf(0.18));
	    BigDecimal total = subtotal.add(igv);

	    venta.setSubtotal(subtotal);
	    venta.setIgv(igv);
	    venta.setTotal(total);

	    // Guardar la venta actualizada
	    return ventaRepository.save(venta);
	}

	
	
}	
