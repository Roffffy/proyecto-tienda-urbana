package com.tienda_urbana.usuarios.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.tienda_urbana.usuarios.model.Usuario;
import com.tienda_urbana.usuarios.repo.UsuarioRepository;


@DataJpaTest
@ActiveProfiles("test")
@DisplayName("Test del repositorio de usuarios en memoria")
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private TestEntityManager entityManager;
    private Usuario us1;
    private Usuario us2;

    @BeforeEach
    void setUp(){
        us1 = entityManager.persistAndFlush(
            new Usuario(null, "Nicolas Gutierrez", "nico.gutirrezp@duocuc.cl", "contra.123", null,  "Cliente", LocalDate.now())
        );
        us2 = entityManager.persistAndFlush(
            new Usuario(null, "Nicolas Saavedra", "nic.saavedrag@duocuc.cl","contraseña312.", null, "Cliente", LocalDate.now())
        );
    }

    @Test
    @DisplayName("save() debe guardar al usuario en la bd y retorar parte de sus datos")
    void save_seDebeGuardarYretornarUsuario(){
        Usuario nuevoUsuario = new Usuario(null, "Cristian Aguirre", "c.aguirre", "Contraseña412.", null, "Cliente", LocalDate.now());
        Usuario usuarioGuardado = repo.save(nuevoUsuario);

        assertNotNull(usuarioGuardado);
        assertEquals(3, repo.count());
    }

    @Test
    @DisplayName("findById() debe retornar un optional con el usuario cuando existe")
    void findById_debeRetornarUsuario_cuandoExiste(){
        Optional<Usuario> resultado = repo.findById(us1.getId());
        assertTrue(resultado.isPresent());
        assertEquals(Optional.of(us1), resultado);
    }

    @Test
    @DisplayName("findById() debe retornar un optional vacio cuando el usuario no existe")
    void findById_debeRetornarOptionalVacio_cuandoNoExiste(){
        Optional<Usuario> resultado = repo.findById(99999L);
        assertFalse(resultado.isPresent());
        assertEquals(Optional.empty(), resultado);
    }

    @Test
    @DisplayName("existsByEmail() debe retornar un true si el usuario existe segun el email indicado")
    void existsByEmail_debeRetornarTrue_cuandoExiste(){
        assertTrue(repo.existsByEmail(us1.getEmail()));
    }

    @Test
    @DisplayName("existsByEmail() debe retornar un false cuando el usuario no existe segun el email indicado")
    void existsByEmail_debeRetornarFalse_cuandoNoExiste(){
        assertFalse(repo.existsByEmail("Correofalso@gmail.com"));
    }

}
