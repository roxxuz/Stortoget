package com.example.stortorget.controller;

import com.example.stortorget.entity.User;
import com.example.stortorget.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserController userController;

    @Autowired
    private UserRepository userRepository;

    @Test
    void saveUser() {

        //given
        User testUser = new User("testUserName",
                "testPass",
                "testPhone",
                "test@email.com",
                "user",
                "testImage");

        //when

        userController.saveUser(testUser, null);

        //then
        User DBUser = userRepository.findByUserName(testUser.getUserName());

        assertThat(DBUser.getUserName()).isEqualTo(testUser.getUserName());

        //remove user from database after test
        userRepository.delete(testUser);

    }

    @Test
    void updateUser() {

        //given
        User testUser = new User("testUserName",
                "testPass",
                "testPhone",
                "test@email.com",
                "user",
                "testImage");

        userRepository.save(testUser);

        testUser.setEmail("updatedTest@email.com");

        //when
        userRepository.save(testUser);

        //then

        User DBUser = userRepository.findByUserName(testUser.getUserName());

        assertThat(DBUser.getEmail()).isEqualTo(testUser.getEmail());

        //remove user from database after test
        userRepository.delete(testUser);

    }
}