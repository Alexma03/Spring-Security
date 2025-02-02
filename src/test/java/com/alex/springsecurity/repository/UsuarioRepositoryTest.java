package com.alex.springsecurity.repository;

import com.alex.springsecurity.model.Perfil;
import com.alex.springsecurity.model.Usuario;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UsuarioRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UsuarioRepository repo;

    @Test
    public void testCreateAdminUser() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Usuario usuario = new Usuario();
        usuario.setEmail("admin@mail.com");
        usuario.setPassword(encoder.encode("admin"));
        usuario.setApellidos("root");
        usuario.setNombre("Admin");
        usuario.setUsername("admin");
        usuario.setDireccion("");
        usuario.setEnabled(1);
        usuario.setFechaRegistro(new java.util.Date());

        // Buscar el perfil de admin existente o crear uno nuevo
        Perfil adminPerfil = entityManager
                .createQuery("SELECT p FROM Perfil p WHERE p.nombre = :nombre", Perfil.class)
                .setParameter("nombre", "ROLE_ADMON")
                .getResultStream()
                .findFirst()
                .orElseGet(() -> {
                    Perfil newPerfil = new Perfil();
                    newPerfil.setNombre("ROLE_ADMON");
                    entityManager.persist(newPerfil);
                    return newPerfil;
                });

        // Buscar el perfil de cliente existente o crear uno nuevo
        Perfil clientPerfil = entityManager
                .createQuery("SELECT p FROM Perfil p WHERE p.nombre = :nombre", Perfil.class)
                .setParameter("nombre", "ROLE_CLIENTE")
                .getResultStream()
                .findFirst()
                .orElseGet(() -> {
                    Perfil newPerfil = new Perfil();
                    newPerfil.setNombre("ROLE_CLIENTE");
                    entityManager.persist(newPerfil);
                    return newPerfil;
                });

        usuario.setPerfiles(List.of(adminPerfil, clientPerfil));

        repo.save(usuario);

        Usuario existUsuario = testEntityManager.find(Usuario.class, usuario.getUsername());

        assertThat(usuario.getEmail()).isEqualTo(existUsuario.getEmail());
        assertThat(existUsuario.getPerfiles()).contains(adminPerfil);
    }

    @Test
    public void testCreateClientUser() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Usuario usuario = new Usuario();
        usuario.setEmail("client@mail.com");
        usuario.setPassword(encoder.encode("client"));
        usuario.setApellidos("Doe");
        usuario.setNombre("Client");
        usuario.setUsername("client");
        usuario.setDireccion("Client Street 123");
        usuario.setEnabled(1);
        usuario.setFechaRegistro(new java.util.Date());

        // Buscar el perfil de cliente existente o crear uno nuevo
        Perfil clientPerfil = entityManager
                .createQuery("SELECT p FROM Perfil p WHERE p.nombre = :nombre", Perfil.class)
                .setParameter("nombre", "ROLE_CLIENTE")
                .getResultStream()
                .findFirst()
                .orElseGet(() -> {
                    Perfil newPerfil = new Perfil();
                    newPerfil.setNombre("ROLE_CLIENTE");
                    entityManager.persist(newPerfil);
                    return newPerfil;
                });

        // Asignar el perfil de cliente al usuario
        usuario.setPerfiles(List.of(clientPerfil));

        repo.save(usuario);

        Usuario existUsuario = testEntityManager.find(Usuario.class, usuario.getUsername());

        assertThat(usuario.getEmail()).isEqualTo(existUsuario.getEmail());
        assertThat(existUsuario.getPerfiles()).contains(clientPerfil);
    }
}