package com.brother.awsses.controller;


import com.brother.awsses.dto.ReceiverInfo;
import com.brother.awsses.service.AmazonSesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("aws-ses-management")
public class EmailController {

    private final AmazonSesService amazonSesService;

    @PostMapping("sendEmail")
    public void sendEmail(@RequestBody ReceiverInfo info) {
        amazonSesService.sendEmail(info.getEmailList(), "",
                "<b>https://www.youtube.com/watch?v=5Eqb_-j3FDA</b>", info.getSubject());
    }
}
