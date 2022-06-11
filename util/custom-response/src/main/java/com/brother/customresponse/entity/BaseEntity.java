package com.brother.customresponse.entity;

import com.brother.customresponse.enums.ActiveStatus;
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
    private Integer activeStatus;

    @PrePersist
    public void prePersistValue() {
        this.createdAt = LocalDateTime.now();
        this.activeStatus = ActiveStatus.ACTIVE.getValue();
    }

    @PreUpdate
    public void preUpdatedValue() {
        this.updatedAt = LocalDateTime.now();
    }
}
