package com.book.bookmanager.domain.author;


import com.book.bookmanager.domain.BaseEntity;
import com.book.bookmanager.domain.book.Book;
import com.book.bookmanager.domain.book.BookAndAuthor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor
@Getter
@ToString
@Entity
public class Author extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String country;

    @OneToMany
    @JoinColumn(name = "author_id")
    @ToString.Exclude
    private List<BookAndAuthor> bookAndAuthors = new ArrayList<>();

    public void addBookAndAuthors(BookAndAuthor... bookAndAuthors) {
        Collections.addAll(this.bookAndAuthors, bookAndAuthors);
    }

    public Author(String name) {
        this.name = name;
    }
}
