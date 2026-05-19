package com.tienda_urbana.catalogo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Modifying()
    @Query("UPDATE Producto p set p.categoria = (SELECT c FROM Categoria c WHERE c.id = 1) WHERE p.categoria.id = :id")
    void reasignarCategoria(@Param("id") Long id);
}
