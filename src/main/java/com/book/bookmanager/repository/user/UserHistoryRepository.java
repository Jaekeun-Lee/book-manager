package com.book.bookmanager.repository.user;

import com.book.bookmanager.domain.user.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
}
