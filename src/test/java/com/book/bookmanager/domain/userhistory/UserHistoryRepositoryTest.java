package com.book.bookmanager.domain.userhistory;

import com.book.bookmanager.domain.user.User;
import com.book.bookmanager.domain.user.UserRepository;
import org.assertj.core.util.Lists;
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
    void createHistoryTest() {
        User user1 = User.builder().name("alice").email("alice@naver.com").build();
        User user2 = User.builder().name("steve").email("steve@naver.com").build();
        User user3 = User.builder().name("coco").email("coco@gmail.com").build();


        userRepository.saveAll(Lists.newArrayList(user1, user2, user3));
        assertEquals(3, userHistoryRepository.findAll().size());
        System.out.println("save===================================");
        userRepository.findAll().forEach(System.out::println);
        System.out.println("save===================================");

        user1.updateEmail("update@update.com");
        userRepository.save(user1);

        System.out.println("update===================================");
        userRepository.findAll().forEach(System.out::println);
        System.out.println("update===================================");
        userHistoryRepository.findAll().forEach(System.out::println);

    }
}