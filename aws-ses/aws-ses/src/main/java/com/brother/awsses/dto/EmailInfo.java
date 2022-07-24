package com.brother.awsses.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmailInfo {
    private String[] receiverAddresses;
    private String[] ccAddresses;
    private String[] bccAddresses;
    private String subject;
}
