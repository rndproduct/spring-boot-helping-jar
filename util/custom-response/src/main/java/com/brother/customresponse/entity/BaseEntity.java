package com.brother.customresponse.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer isActive;

    @PrePersist
    public void prePersistValue() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdatedValue() {
        this.updatedAt = LocalDateTime.now();
    }
}
