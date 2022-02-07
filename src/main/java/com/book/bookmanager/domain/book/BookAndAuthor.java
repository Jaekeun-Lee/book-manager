package com.book.bookmanager.domain.book;

import com.book.bookmanager.domain.author.Author;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@ToString
@Getter
public class BookAndAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Author author;

    public BookAndAuthor(Book book, Author author) {
        this.book = book;
        this.author = author;
    }
}
