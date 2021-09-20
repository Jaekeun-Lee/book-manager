package com.book.bookmanager.domain.listener;

import com.book.bookmanager.domain.user.User;
import com.book.bookmanager.domain.user.UserHistory;
import com.book.bookmanager.repository.user.UserHistoryRepository;
import com.book.bookmanager.util.BeanUtils;

import javax.persistence.PostPersist;
import javax.persistence.PreUpdate;

public class UserEntityListener {

    @PostPersist
    @PreUpdate
    public void postPersistAndPreUpdate(Object o) {
        if (o instanceof User) {

            UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

            User user = (User) o;

            UserHistory userHistory = new UserHistory();
            userHistory.setUserId(user.getId());
            userHistory.setName(user.getName());
            userHistory.setEmail(user.getEmail());

            userHistoryRepository.save(userHistory);
        }
    }
}
