package com.book.bookmanager.domain;

import java.time.LocalDateTime;

public interface Auditable {

    void setCreatedAt(LocalDateTime createdAt);
    void setUpdatedAt(LocalDateTime updatedAt);

    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
}
