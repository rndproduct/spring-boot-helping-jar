package com.brother.awsses.controller;


import com.brother.awsses.dto.EmailInfo;
import com.brother.awsses.service.SendMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("aws-ses-management")
public class EmailController {

    private final SendMailService service;

    @PostMapping("with-file-attachment")
    public void sendFileWithAttachment(@ModelAttribute EmailInfo info, List<MultipartFile> files) {
        service.sendFileAttachmentEmail(info, files);
    }
}
