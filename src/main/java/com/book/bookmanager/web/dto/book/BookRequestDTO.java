package com.book.bookmanager.web.dto.book;

import com.book.bookmanager.domain.book.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDTO {
    private String name;
    private String category;
    private Long authorId;
    private Long publisherId;

    public Book toEntity() {
        return Book.builder()
                .name(name)
                .category(category)
                .authorId(authorId)
                .publisherId(publisherId)
                .build();
    }
}
