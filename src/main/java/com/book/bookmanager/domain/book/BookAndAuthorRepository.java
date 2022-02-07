package com.book.bookmanager.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookAndAuthorRepository extends JpaRepository<BookAndAuthor, Long> {

}
