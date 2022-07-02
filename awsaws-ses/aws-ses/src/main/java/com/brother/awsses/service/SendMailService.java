package com.brother.awsses.service;

import com.brother.awsses.dto.EmailInfo;
import com.brother.awsses.util.ApplicationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SendMailService {
    private final AmazonSesService amazonSesService;

    public void sendFileAttachmentEmail(EmailInfo info, List<MultipartFile> files) {
        List<File> fileList = new ArrayList<>();
        files.forEach(file -> fileList.add(ApplicationUtil.convertMultiPartFileToFile(file)));
        amazonSesService.sendNonHtmlMail(info.getReceiverAddresses(), info.getSubject(), "Dear Concern", fileList,
                info.getCcAddresses(), info.getBccAddresses());
    }
}
