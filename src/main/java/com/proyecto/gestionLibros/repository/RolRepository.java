package com.libro.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libro.demo.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{

}
