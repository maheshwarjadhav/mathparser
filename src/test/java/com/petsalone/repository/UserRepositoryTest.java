package com.petsalone.repository;

import com.petsalone.model.Role;
import com.petsalone.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test_User_Creation_Retrieval_Deletion() {
        User user = new User();
        user.setUsername("elmyraduff");
        user.setPassword("MontanaMax!!");
        user.setRoles(Set.of(new Role("USER")));
        userRepository.save(user);

        User user1 = userRepository.findByUsername("elmyraduff");
        Assertions.assertThat(user1).extracting(User::getPassword).isEqualTo("MontanaMax!!");
        userRepository.deleteAll();
        Assertions.assertThat(userRepository.findAll()).isEmpty();
    }

}
