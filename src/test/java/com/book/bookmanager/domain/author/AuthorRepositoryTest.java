package com.book.bookmanager.domain.author;

import com.book.bookmanager.domain.book.Book;
import com.book.bookmanager.domain.book.BookAndAuthor;
import com.book.bookmanager.domain.book.BookAndAuthorRepository;
import com.book.bookmanager.domain.book.BookRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookAndAuthorRepository bookAndAuthorRepository;

    @Test
    @Transactional
    void manyToManyTest() {
        Book test1 = givenBook("test1");
        Book test2 = givenBook("test2");
        Book test3 = givenBook("test3");
        Book test4 = givenBook("test4");

        Author author1 = givenAuthor("martin");
        Author author2 = givenAuthor("dennis");

        BookAndAuthor bookAndAuthor1 = givenBookAndAuthor(test1, author1);
        BookAndAuthor bookAndAuthor2 = givenBookAndAuthor(test2, author2);
        BookAndAuthor bookAndAuthor3 = givenBookAndAuthor(test3, author1);
        BookAndAuthor bookAndAuthor4 = givenBookAndAuthor(test3, author2);
        BookAndAuthor bookAndAuthor5 = givenBookAndAuthor(test4, author1);
        BookAndAuthor bookAndAuthor6 = givenBookAndAuthor(test4, author2);


        test1.addBookAndAuthors(bookAndAuthor1);
        test2.addBookAndAuthors(bookAndAuthor2);
        test3.addBookAndAuthors(bookAndAuthor3, bookAndAuthor4);
        test4.addBookAndAuthors(bookAndAuthor5, bookAndAuthor6);

        author1.addBookAndAuthors(bookAndAuthor1, bookAndAuthor3, bookAndAuthor5);
        author2.addBookAndAuthors(bookAndAuthor2, bookAndAuthor4, bookAndAuthor6);

        bookRepository.saveAll(Lists.newArrayList(test1, test2, test3, test4));
        authorRepository.saveAll(Lists.newArrayList(author1, author2));

        bookRepository.findAll().get(2).getBookAndAuthors().forEach(bookAndAuthor -> System.out.println(bookAndAuthor.getAuthor()));
        authorRepository.findAll().get(0).getBookAndAuthors().forEach(bookAndAuthor -> System.out.println(bookAndAuthor.getBook()));
    }


    private Book givenBook(String name) {
        return bookRepository.save(Book.builder()
                .name(name)
                .category("Test Category")
                .build());
    }

    private Author givenAuthor(String name) {
        return authorRepository.save(new Author(name));
    }

    private BookAndAuthor givenBookAndAuthor(Book book, Author author) {
        return bookAndAuthorRepository.save(new BookAndAuthor(book, author));
    }
}