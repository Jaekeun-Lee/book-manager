package com.book.bookmanager.domain.review;

import com.book.bookmanager.domain.BaseEntity;
import com.book.bookmanager.domain.book.Book;
import com.book.bookmanager.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@ToString
@Entity
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private float score;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

    @Builder
    public Review(String title, String content, float score, User user, Book book) {
        this.title = title;
        this.content = content;
        this.score = score;
        this.user = user;
        this.book = book;
    }
}
