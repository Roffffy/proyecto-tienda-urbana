package com.tienda_urbana.catalogo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tienda_urbana.catalogo.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

    boolean existsByNombre(String nombre);


    @Query("SELECT c FROM Categoria c WHERE c.nombre LIKE CONCAT('%', :nombre, '%')")
    List<Categoria> buscarCategoria(@Param("nombre") String nombre);

}
