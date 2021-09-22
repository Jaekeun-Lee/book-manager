package com.book.bookmanager.domain.user;

import com.book.bookmanager.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@ToString
@Entity
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

    @Builder
    public User(@NonNull String name, @NonNull String email, Gender gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public User emailUpdate(String email) {
        this.email = email;

        return this;
    }
}
