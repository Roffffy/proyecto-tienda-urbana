package com.tienda_urbana.usuarios.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda_urbana.usuarios.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
    
}
