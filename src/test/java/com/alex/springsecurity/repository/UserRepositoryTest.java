package com.alex.springsecurity.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;

/*    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("alex03marcos@gmail.com");
        user.setPassword("alexPrueba");
        user.setApellidos("Marcos");
        user.setNombre("Alejandro");
        user.setUsername("alex03");
        user.setDireccion("Calle 123");
        user.setEnabled(1);

        User savedUser = repo.save(user);

        User existUser = entityManager.find(User.class, savedUser.getEmail());

        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());

    }*/
}