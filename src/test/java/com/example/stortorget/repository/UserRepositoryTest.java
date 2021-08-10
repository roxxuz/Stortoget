package com.example.stortorget.repository;

import com.example.stortorget.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @Test
    void itShouldFindByUserName() {
        //given
        User testUser = new User("testUserName",
                "testPass",
                "testPhone",
                "test@email.com",
                "user",
                "testImage");
        underTest.save(testUser);

        //when
        User DBUser = underTest.findByUserName("testUserName");

        //then
        assertThat(DBUser).isEqualTo(testUser);

    }
}