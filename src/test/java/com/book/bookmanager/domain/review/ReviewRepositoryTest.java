package com.book.bookmanager.domain.review;

import com.book.bookmanager.domain.book.Book;
import com.book.bookmanager.domain.book.BookRepository;
import com.book.bookmanager.domain.publisher.Publisher;
import com.book.bookmanager.domain.publisher.PublisherRepository;
import com.book.bookmanager.domain.user.User;
import com.book.bookmanager.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewRepositoryTest {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    PublisherRepository publisherRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;


    @Test
    @Transactional
    void bookRelationTest() {

        givenReview();

        User user = givenUser();

        System.out.println("Review ::: " + user.getReviews().get(0));
        System.out.println("Book ::: " + user.getReviews().get(0).getBook());
        System.out.println("Publisher ::: " + user.getReviews().get(0).getBook().getPublisher());

    }

    private void givenReview() {
        reviewRepository.save(Review.builder()
                .title("만족합니다")
                .content("책이 너무 좋아요")
                .score(5.0f)
                .user(givenUser())
                .book(givenBook(givenPublisher()))
                .build());
    }

    private Publisher givenPublisher() {
        return publisherRepository.save(Publisher.builder().name("Lee").build());
    }

    private User givenUser() {
        return userRepository.findById(1L).orElseThrow(RuntimeException::new);
    }

    private Book givenBook(Publisher publisher) {
        return bookRepository.save(Book.builder()
                .name("Test Book")
                .category("Test Category")
                .authorId(1L)
                .publisher(publisher)
                .build());
    }

}