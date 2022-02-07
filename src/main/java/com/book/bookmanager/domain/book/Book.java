package com.book.bookmanager.domain.book;

import com.book.bookmanager.domain.author.Author;
import com.book.bookmanager.domain.BaseEntity;
import com.book.bookmanager.domain.bookreviewinfo.BookReviewInfo;
import com.book.bookmanager.domain.publisher.Publisher;
import com.book.bookmanager.domain.review.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor
@Getter
@ToString
@Entity
public class Book extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    @OneToOne(mappedBy = "book")
    @ToString.Exclude
    private BookReviewInfo bookReviewInfo;

    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne
    @ToString.Exclude
    private Publisher publisher;

    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<BookAndAuthor> bookAndAuthors = new ArrayList<>();


    @Builder
    public Book(String name, String category, Publisher publisher) {
        this.name = name;
        this.category = category;
        this.publisher = publisher;
    }

    public void addBookAndAuthors(BookAndAuthor... bookAndAuthors) {
        Collections.addAll(this.bookAndAuthors, bookAndAuthors);
    }
}
