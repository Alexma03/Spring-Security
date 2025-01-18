package com.alex.springsecurity.repository;

import com.alex.springsecurity.model.Perfil;
import com.alex.springsecurity.model.User;
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
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository repo;

    @Test
    public void testCreateAdminUser() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setEmail("admin@mail.com");
        user.setPassword(encoder.encode("admin"));
        user.setApellidos("root");
        user.setNombre("Admin");
        user.setUsername("admin");
        user.setDireccion("");
        user.setEnabled(1);

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

        // Asignar el perfil de admin al usuario
        user.setPerfiles(List.of(adminPerfil));

        repo.save(user);

        User existUser = testEntityManager.find(User.class, user.getUsername());

        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
        assertThat(existUser.getPerfiles()).contains(adminPerfil);
    }

    @Test
    public void testCreateClientUser() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setEmail("client@mail.com");
        user.setPassword(encoder.encode("client"));
        user.setApellidos("Doe");
        user.setNombre("Client");
        user.setUsername("client");
        user.setDireccion("Client Street 123");
        user.setEnabled(1);

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
        user.setPerfiles(List.of(clientPerfil));

        repo.save(user);

        User existUser = testEntityManager.find(User.class, user.getUsername());

        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
        assertThat(existUser.getPerfiles()).contains(clientPerfil);
    }
}