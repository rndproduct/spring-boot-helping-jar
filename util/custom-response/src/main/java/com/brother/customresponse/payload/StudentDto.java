package com.brother.customresponse.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Setter
@Getter
public class StudentDto {
    @NotBlank(message = "Please Provide Student Name")
    private String name;
    private String email;
    private Set<AddressDto> address;
}
