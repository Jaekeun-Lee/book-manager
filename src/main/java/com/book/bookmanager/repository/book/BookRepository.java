package com.book.bookmanager.repository.book;

import com.book.bookmanager.domain.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
