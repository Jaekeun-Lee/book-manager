package com.book.bookmanager.repository;

import com.book.bookmanager.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @Order(1)
    void create() {

        User expectUser6 = new User("alice", "alice@naver.com");
        User expectUser7 = new User("steve", "steve@naver.com");
        User expectUser8 = new User("coco", "coco@gmail.com");

        userRepository.save(expectUser6);
        userRepository.saveAll(Lists.newArrayList(expectUser7, expectUser8));

        List<User> users = userRepository.findAll();
        User actualUser5 = users.get(4);    // data.sql
        User actualUser6 = users.get(5);    // expectUser6 - save()
        User actualUser7 = users.get(6);    // expectUser7 - saveAll()

        // data.sql 에서 넣은 5개의 유저 데이터
        // + create() 메소드에서 넣은 3개의 유저 데이터
        assertEquals(8, users.size());

        // data.sql insert
        // [expectUser5 = id=5, name="jack", email="jack@naver.com"]
        assertEquals("jack", actualUser5.getName());
        assertEquals("jack@naver.com", actualUser5.getEmail());

        // save() insert
        assertEquals(expectUser6.getName(), actualUser6.getName());
        assertEquals(expectUser6.getEmail(), actualUser6.getEmail());

        // saveAll() insert
        assertEquals(expectUser7.getName(), actualUser7.getName());
        assertEquals(expectUser7.getEmail(), actualUser7.getEmail());

        // create() 메소드에서 넣은 데이터 삭제
        userRepository.deleteAll(Lists.newArrayList(expectUser6, expectUser7, expectUser8));
    }


    @Test
    @Order(2)
    void pagingTest() {
        Page<User> users = userRepository.findAll(PageRequest.of(0, 3));

        /*
        System.out.println("page : " + users);
        System.out.println("totalElements : " + users.getTotalElements());
        System.out.println("totalPages : " + users.getTotalPages());
        System.out.println("numberOfElements : " + users.getNumberOfElements());
        System.out.println("sort : " + users.getSort());
        System.out.println("size : " + users.getSize());

        users.getContent().forEach(System.out::println);
        */

        assertEquals(5, users.getTotalElements());
        assertEquals(2, users.getTotalPages());
        assertEquals(3, users.getNumberOfElements());

    }

    @Test
    @Order(3)
    void queryByExampleTest() {

        User user = new User();
        user.setName("jack");
        user.setEmail("naver.com");

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("name")
                .withMatcher("email", endsWith());
//
//        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("email", contains());
        Example<User> example = Example.of(user, matcher);

        userRepository.findAll(example).forEach(System.out::println);
    }

    @Test
    @Order(4)
    void deleteTest() {

//        userRepository.deleteAll(userRepository.findAllById(Lists.newArrayList(1L, 3L)));
//        userRepository.deleteInBatch(userRepository.findAllById(Lists.newArrayList(1L, 3L)));
//        userRepository.deleteAll();
        userRepository.deleteAllInBatch();
    }

}