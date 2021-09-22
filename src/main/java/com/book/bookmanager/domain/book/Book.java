package com.book.bookmanager.domain.book;

import com.book.bookmanager.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    private Long authorId;

    private Long publisherId;

    @Builder
    public Book(String name, String category, Long authorId, Long publisherId) {
        this.name = name;
        this.category = category;
        this.authorId = authorId;
        this.publisherId = publisherId;
    }
}
