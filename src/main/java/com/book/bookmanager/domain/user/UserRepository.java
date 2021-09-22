package com.book.bookmanager.domain.user;

import com.book.bookmanager.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByName(String name);

    // select
    Optional<User> findByEmail(String email);
    Optional<User> getByEmail(String email);
    Optional<User> readByEmail(String email);
    Optional<User> queryByEmail(String email);
    Optional<User> searchByEmail(String email);
    Optional<User> streamByEmail(String email);
    Optional<User> findUserByEmail(String email);


    // Optional<User> findByA(String email);                        // A는 Entity에 정의된 필드가 아니기 때문에 오류 발생
    Optional<User> findFirstByName(String name);
    List<User> findFirst2ByName(String name);
    Optional<User> findTopByName(String name);
    List<User> findTop2ByName(String name);


    // Optional<User> findLastByName(String name);                  // Last는 정의된 키워드가 아니기 때문에 무시됨(=findByName)
    Optional<User> findByNameAndEmail(String name, String email);
    List<User> findByNameOrEmail(String name, String email);


    List<User> findByCreatedAtAfter(LocalDateTime time);            // Before, After 는 초과 미만
    List<User> findByCreatedAtBefore(LocalDateTime time);           // Before, After 는 초과 미만
    List<User> findByCreatedAtGreaterThan(LocalDateTime time);      // 초과
    List<User> findByCreatedAtGreaterThanEqual(LocalDateTime time); // 이상
    List<User> findByIdBetween(Long id, Long id2);                  // A이상 B이하

    List<User> findByNameIn(List<String> names);
    List<User> findByNameLike(String name);
    List<User> findByNameStartingWith(String name);
    List<User> findByNameEndingWith(String name);
    List<User> findByNameContains(String name);


    List<User> findByNameOrderByIdDesc(String name);
    List<User> findByNameOrderByIdDescEmailAsc(String name);
    List<User> findByName(String name, Sort sort);


    Page<User> findByName(String name, Pageable pageable);

    @Query(value = "select * from user limit 1;", nativeQuery = true)
    Map<String, Object> findRowRecord();

}
