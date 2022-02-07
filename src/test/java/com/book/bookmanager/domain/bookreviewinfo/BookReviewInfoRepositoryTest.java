package com.book.bookmanager.domain.bookreviewinfo;

import com.book.bookmanager.domain.book.Book;
import com.book.bookmanager.domain.book.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BookReviewInfoRepositoryTest {

    @Autowired
    BookReviewInfoRepository bookReviewInfoRepository;

    @Autowired
    BookRepository bookRepository;

    @Test
    void crudTest() {

        BookReviewInfo expect = BookReviewInfo.builder()
                .book(givenBook())
                .averageReviewScore(4.5f)
                .reviewCount(2)
                .build();

        BookReviewInfo actual = bookReviewInfoRepository.save(expect);

        assertNotNull(actual.getBook());
        assertEquals(expect.getAverageReviewScore(), actual.getAverageReviewScore());
        assertEquals(expect.getReviewCount(), actual.getReviewCount());

    }

    @Test
    void crud() {

    }


    private Book givenBook() {
        return bookRepository.save(Book.builder()
                .name("Test Name")
                .category("Test Category")
                .build());
    }

}