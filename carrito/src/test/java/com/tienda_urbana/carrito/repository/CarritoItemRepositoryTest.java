package com.tienda_urbana.carrito.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.tienda_urbana.carrito.model.Carrito;
import com.tienda_urbana.carrito.model.CarritoItem;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("Test del repositorio de items de carrito con H2")
public class CarritoItemRepositoryTest {

    @Autowired
    private CarritoRepository carritoRepo;

    @Autowired
    private CarritoItemRepository itemRepo;

    @Test
    @DisplayName("findByCarrito() debe retornar items relacionados a un carrito")
    void findByCarrito_debeRetornarItemsRelacionadosAUnCarrito() {
        Carrito carrito = carritoRepo.save(new Carrito(null, 8L));
        CarritoItem item = itemRepo.save(new CarritoItem(null, 3, 1L, carrito));

        List<CarritoItem> resultado = itemRepo.findByCarrito(carrito);

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getCantidad()).isEqualTo(3);
        assertThat(resultado.get(0).getProductoId()).isEqualTo(1L);
    }
}
