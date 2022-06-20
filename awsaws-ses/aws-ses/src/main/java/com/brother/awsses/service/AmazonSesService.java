package com.brother.awsses.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmazonSesService {
    private static final String CHAR_SET = "UTF-8";
    @Value("${aws.access.key}")
    private String awsAccessKey;
    @Value("${aws.secret.key}")
    private String awsSecretKey;
    @Value("${aws.region}")
    private String region;
    @Value("${aws.ses.defaultEmail}")
    private String sender;

    public void sendEmail(List<String> receivers, String textContent, String htmlContent,
                          String subject) {
        AmazonSimpleEmailService simpleEmailService = AmazonSimpleEmailServiceClientBuilder.standard().withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
                .build();

        SendEmailRequest request = new SendEmailRequest().
                withDestination(new Destination().withToAddresses(receivers))
                .withMessage(new Message()
                        .withBody(new Body().withHtml(new Content().withCharset(CHAR_SET).withData(htmlContent))
                                .withText(new Content().withCharset(CHAR_SET).withData(textContent)))
                        .withSubject(new Content().withCharset(CHAR_SET).withData(subject)))
                .withSource(sender);
        simpleEmailService.sendEmail(request);
    }
}
