package com.book.bookmanager.domain.userhistory;

import com.book.bookmanager.domain.userhistory.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
}
