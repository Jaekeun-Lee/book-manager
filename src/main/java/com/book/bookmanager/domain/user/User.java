package com.book.bookmanager.domain.user;

import com.book.bookmanager.domain.Auditable;
import com.book.bookmanager.domain.BaseEntity;
import com.book.bookmanager.domain.listener.UserEntityListener;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
@Entity
@EntityListeners(value = UserEntityListener.class)
public class User extends BaseEntity implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

}
