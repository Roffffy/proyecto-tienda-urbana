package com.tienda_urbana.carrito.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.tienda_urbana.carrito.model.Carrito;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("Test del repositorio de carrito con H2")
public class CarritoRepositoryTest {

    @Autowired
    private CarritoRepository carritoRepo;

    @Test
    @DisplayName("findByUsuarioId() debe retornar carrito existente")
    void findByUsuarioId_debeRetornarCarritoExistente() {
        Carrito carrito = carritoRepo.save(new Carrito(null, 5L));

        Optional<Carrito> resultado = carritoRepo.findByUsuarioId(5L);

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getUsuarioId()).isEqualTo(5L);
    }
}
