package com.book.bookmanager.repository.user;

import com.book.bookmanager.domain.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserHistoryRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserHistoryRepository userHistoryRepository;

    @Test
    void userHistoryTest() {

        User user = new User();
        user.setEmail("jack-new@naver.com");
        user.setName("jack-new");

        userRepository.save(user);
        userHistoryRepository.findAll().forEach(System.out::println);
    }
}