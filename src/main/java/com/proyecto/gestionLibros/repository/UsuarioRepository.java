package com.proyecto.gestionLibros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.libro.demo.entity.Rol;
import com.libro.demo.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	@Query(value="SELECT u.idusuario,u.usuario,u.nombres,u.apellidos,u.clave,u.idrol FROM usuario u where u.usuario = :username",nativeQuery = true)
	public Usuario findByUsuario(@Param("username") String username);
	
	@Query(value="SELECT u.idusuario,u.usuario,u.nombres,u.apellidos,u.clave,u.idrol FROM usuario u where u.usuario = :username and u.clave = :clave",nativeQuery = true)
	public Usuario findByUsuarioAndClave(@Param("username") String username,@Param("clave") String clave);
	
	@Query(value = "SELECT u.* FROM usuario u INNER JOIN rol r ON u.idrol = r.idrol WHERE r.descripcion = 'ASESOR'", 
		       nativeQuery = true)
		List<Usuario> findUsuariosByRolAsesor();

}
