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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

//        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("email", contains());
        Example<User> example = Example.of(user, matcher);

        userRepository.findAll(example).forEach(System.out::println);
    }

    @Test
    @Order(4)
    void updateTest() {

        User expect = userRepository.findById(1L).orElseThrow(NullPointerException::new);
        expect.setEmail("update@gmail.com");

        User actual = userRepository.save(expect);

        assertEquals(expect.getEmail(), actual.getEmail());
    }


    @Test
    @Order(5)
    void queryMethodTest() {

        String expectName = "jack";
        String expectEmail = "jack@naver.com";

        // select
        assertEquals(expectName, userRepository.findByName("jack").get().getName());
        assertEquals(expectEmail, userRepository.findByEmail("jack@naver.com").get().getEmail());
        assertEquals(expectEmail, userRepository.getByEmail("jack@naver.com").get().getEmail());
        assertEquals(expectEmail, userRepository.readByEmail("jack@naver.com").get().getEmail());
        assertEquals(expectEmail, userRepository.queryByEmail("jack@naver.com").get().getEmail());
        assertEquals(expectEmail, userRepository.searchByEmail("jack@naver.com").get().getEmail());
        assertEquals(expectEmail, userRepository.streamByEmail("jack@naver.com").get().getEmail());
        assertEquals(expectEmail, userRepository.findUserByEmail("jack@naver.com").get().getEmail());

        // A는 Entity에 정의된 필드가 아니기 때문에 오류 발생
        // userRepository.findByA("jack@naver.com");


        assertEquals(expectName, userRepository.findFirstByName(expectName).get().getName());
        assertEquals(2, userRepository.findFirst2ByName(expectName).size());
        assertEquals(expectName, userRepository.findTopByName(expectName).get().getName());
        assertEquals(2, userRepository.findTop2ByName(expectName).size());
        assertEquals(expectName, userRepository.findByNameAndEmail(expectName, expectEmail).get().getName());
        assertEquals(2, userRepository.findByNameOrEmail(expectName,"james@gmail.com").size());

        assertEquals(5, userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L)).size());
        assertEquals(5, userRepository.findByCreatedAtBefore(LocalDateTime.now().plusDays(1L)).size());
        assertEquals(5, userRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)).size());
        assertEquals(5, userRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)).size());
        assertEquals(3, userRepository.findByIdBetween(1L, 3L).size());

        assertEquals(3, userRepository.findByNameIn(Lists.newArrayList("jack", "dennis")).size());
        assertEquals(2, userRepository.findByNameLike("%ac%").size());
        assertEquals(1, userRepository.findByNameStartingWith("d").size());
        assertEquals(1, userRepository.findByNameEndingWith("s").size());
        assertEquals(2, userRepository.findByNameContains("ac").size());


    }

    @Test
    @Order(6)
    void deleteTest() {

//        userRepository.deleteAll(userRepository.findAllById(Lists.newArrayList(1L, 3L)));
//        userRepository.deleteInBatch(userRepository.findAllById(Lists.newArrayList(1L, 3L)));
//        userRepository.deleteAll();
        userRepository.deleteAllInBatch();
    }


}