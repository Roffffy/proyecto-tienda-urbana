package com.tienda_urbana.carrito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda_urbana.carrito.model.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito,Long> {

}
