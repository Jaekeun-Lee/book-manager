package com.book.bookmanager.domain.userhistory;

import com.book.bookmanager.domain.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@ToString
@Entity
public class UserHistory extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private String name;

    private String email;

}
