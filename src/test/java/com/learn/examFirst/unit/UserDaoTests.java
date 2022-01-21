package com.learn.examFirst.unit;

import com.learn.examFirst.domain.Users;
import com.learn.examFirst.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.learn.examFirst.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserDaoTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void givenUserDetails_whenAddUserDetails_thenSaveUser() {
        //Arrange
        Users user = createUserData();

        //Act
        Users expectedUser = userRepository.save(user);
        List<Users> expectedUserByName = userRepository.findByNameContains(FILTER_BY_NAME);
        List<Users> expectedUserByNoMatchingName = userRepository.findByNameContains(FILTER_BY_NO_MATCHING_NAME);
        List<Users> expectedUserByEmail = userRepository.findByEmailContains(FILTER_BY_EMAIL);

        //Assert
        assertEquals(expectedUser.getName(), user.getName());
        assertEquals(2, expectedUserByName.size());
        assertEquals(0, expectedUserByNoMatchingName.size());
        assertEquals(2, expectedUserByEmail.size());

    }
}
