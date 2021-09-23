package com.book.bookmanager.domain.userhistory;

import com.book.bookmanager.domain.BaseEntity;
import com.book.bookmanager.domain.user.User;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@ToString
@Entity
public class UserHistory extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    private String name;

    private String email;

    @ManyToOne
    @ToString.Exclude
    private User user;

    @Builder
    public UserHistory(Long userId, String name, String email, User user) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.user = user;
    }
}
