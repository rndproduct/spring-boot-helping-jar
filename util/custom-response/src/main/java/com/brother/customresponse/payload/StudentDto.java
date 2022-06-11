package com.brother.customresponse.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class StudentDto {
    @NotBlank(message = "Please Provide Student Name")
    private String name;
    private String email;
    private Set<AddressDto> address;
}
