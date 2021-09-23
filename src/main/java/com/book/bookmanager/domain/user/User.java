package com.book.bookmanager.domain.user;

import com.book.bookmanager.domain.BaseEntity;
import com.book.bookmanager.domain.listener.UserEntityListener;
import com.book.bookmanager.domain.userhistory.UserHistory;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@ToString
@Entity
@EntityListeners(value = UserEntityListener.class)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private List<UserHistory> userHistories = new ArrayList<>();

    @Builder
    public User(@NonNull String name, @NonNull String email, Gender gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public User updateEmail(String email) {
        this.email = email;

        return this;
    }
}
