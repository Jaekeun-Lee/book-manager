package com.book.bookmanager.domain.bookreviewinfo;

import com.book.bookmanager.domain.BaseEntity;
import com.book.bookmanager.domain.book.Book;
import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@Getter
@ToString
@Entity
public class BookReviewInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    private Book book;

    private float averageReviewScore;

    private int reviewCount;

    @Builder
    public BookReviewInfo(Book book, float averageReviewScore, int reviewCount) {
        this.book = book;
        this.averageReviewScore = averageReviewScore;
        this.reviewCount = reviewCount;
    }
}
