package com.brother.customresponse.payload;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseDto {

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer isActive;
}
