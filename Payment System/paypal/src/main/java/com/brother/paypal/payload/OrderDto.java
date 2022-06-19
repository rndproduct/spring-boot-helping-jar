package com.brother.paypal.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDto {
    private Double price;
    private String currency;
    private String method;
    private String intent;
    private String description;

}
