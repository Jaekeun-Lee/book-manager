package com.book.bookmanager.domain.listener;


import com.book.bookmanager.domain.user.User;
import com.book.bookmanager.domain.userhistory.UserHistory;
import com.book.bookmanager.domain.userhistory.UserHistoryRepository;
import com.book.bookmanager.util.BeanUtils;

import javax.persistence.PostPersist;
import javax.persistence.PreUpdate;

public class UserEntityListener {

    @PostPersist
    @PreUpdate
    public void userHistoryPostPersistAndPostUpdate(Object o) {
        if (o instanceof User) {
            UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

            User user = (User) o;

            UserHistory userHistory = UserHistory.builder()
                    .userId(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .build();

            userHistoryRepository.save(userHistory);
        }
    }
}
