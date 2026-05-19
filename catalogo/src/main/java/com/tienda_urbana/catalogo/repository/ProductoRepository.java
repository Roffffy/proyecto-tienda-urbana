package com.tienda_urbana.catalogo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tienda_urbana.catalogo.model.Categoria;
import com.tienda_urbana.catalogo.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{


    @Query("SELECT p FROM Producto p WHERE p.nombre LIKE CONCAT('%', :nombre, '%')")
    List<Producto> buscarProducto(@Param("nombre") String nombre);

    List<Producto> findByCategoria(Categoria categoria);
}
