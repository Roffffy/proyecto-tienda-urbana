package com.tienda_urbana.carrito.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda_urbana.carrito.model.Carrito;
import com.tienda_urbana.carrito.model.CarritoItem;

@Repository
public interface CarritoItemRepository extends JpaRepository<CarritoItem,Long> {

    List<CarritoItem> findByCarrito(Carrito carrito);

}
