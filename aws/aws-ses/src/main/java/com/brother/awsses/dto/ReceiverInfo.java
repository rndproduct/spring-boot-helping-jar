package com.brother.awsses.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ReceiverInfo {
    private List<String> emailList;
    private String subject;
}
