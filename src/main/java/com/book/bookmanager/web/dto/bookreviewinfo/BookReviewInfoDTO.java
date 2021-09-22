package com.book.bookmanager.web.dto.bookreviewinfo;

import com.book.bookmanager.domain.book.Book;
import com.book.bookmanager.domain.bookreviewinfo.BookReviewInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookReviewInfoDTO {

    private Long id;
    private Book book;
    private float averageReviewScore;
    private int reviewCount;

    public BookReviewInfo toEntity() {
        return BookReviewInfo.builder()
                .book(book)
                .averageReviewScore(averageReviewScore)
                .reviewCount(reviewCount)
                .build();
    }
}
