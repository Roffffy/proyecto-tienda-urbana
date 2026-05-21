package com.tienda_urbana.ordenes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda_urbana.ordenes.model.Orden;

@Repository
public interface OrdenRepository extends JpaRepository<Orden,Long>{

}
