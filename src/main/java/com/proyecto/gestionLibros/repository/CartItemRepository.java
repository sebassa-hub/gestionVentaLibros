package com.proyecto.gestionLibros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.gestionLibros.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> { 
    
}