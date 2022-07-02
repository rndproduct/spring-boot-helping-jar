package com.brother.awsses.service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.RawMessage;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendRawEmailRequest;
import com.amazonaws.services.simpleemail.model.SendTemplatedEmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class AmazonSesService {
    private static final String CHAR_SET = "UTF-8";
    @Value("${aws.ses.defaultEmail}")
    private String sender;
    private final AmazonSimpleEmailService amazonSimpleEmailService;

    public void sendEmail(String[] receiverAddresses, String textContent, String htmlContent,
                          String subject, String[] ccAddress, String[] bccAddress) {

        Destination destination = new Destination();
        destination.setToAddresses(Arrays.asList(receiverAddresses));
        if (!CollectionUtils.isEmpty(Arrays.asList(ccAddress))) {
            destination.setCcAddresses(Arrays.asList(ccAddress));
        }
        if (CollectionUtils.isEmpty(Arrays.asList(bccAddress))) {
            destination.setBccAddresses(Arrays.asList(bccAddress));
        }
        SendEmailRequest request = new SendEmailRequest().
                withDestination(destination)
                .withMessage(new Message()
                        .withBody(new Body().withHtml(new Content().withCharset(CHAR_SET).withData(htmlContent))
                                .withText(new Content().withCharset(CHAR_SET).withData(textContent)))
                        .withSubject(new Content().withCharset(CHAR_SET).withData(subject)))
                .withSource(sender);
        amazonSimpleEmailService.sendEmail(request);
    }

    public void sendAwsTemplateEmail(List<String> receiverAddresses, String templateName, String templateJsonData,
                                     List<String> ccAddresses, List<String> bccAddresses) {
        Destination destination = new Destination();
        destination.setToAddresses(receiverAddresses);
        if (!CollectionUtils.isEmpty(ccAddresses)) {
            destination.setCcAddresses(ccAddresses);
        }
        if (!CollectionUtils.isEmpty(bccAddresses)) {
            destination.setBccAddresses(bccAddresses);
        }
        SendTemplatedEmailRequest templatedEmailRequest = new SendTemplatedEmailRequest();
        templatedEmailRequest.withDestination(destination);
        templatedEmailRequest.withTemplate(templateName);
        templatedEmailRequest.withTemplateData(templateJsonData);
        templatedEmailRequest.withSource(sender);
        amazonSimpleEmailService.sendTemplatedEmail(templatedEmailRequest);
    }

    public void sendMail(String[] receiverAddresses, String subject, String text, Boolean isHtml,
                         List<File> attachments, String[] ccAddresses, String[] bccAddresses) {
        try {
            Session session = Session.getInstance(new Properties(System.getProperties()));
            MimeMessage message = new MimeMessage(session);
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(receiverAddresses);
            if (ccAddresses != null) {
                helper.setCc(ccAddresses);
            }
            if (bccAddresses != null) {
                helper.setBcc(bccAddresses);
            }
            helper.setSubject(subject);
            helper.setText(text, isHtml);
            if (attachments != null && !attachments.isEmpty()) {
                for (File file : attachments) {
                    helper.addAttachment(file.getName(), file);
                }
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            message.writeTo(outputStream);
            RawMessage rawMessage = new RawMessage(ByteBuffer.wrap(outputStream.toByteArray()));

            SendRawEmailRequest rawEmailRequest = new SendRawEmailRequest(rawMessage);
            amazonSimpleEmailService.sendRawEmail(rawEmailRequest);
        } catch (Exception e) {
            System.out.println("Problem with sending email to: " + receiverAddresses + " error message: " + e.getMessage());

        }
    }

    public void sendHtmlMail(String[] receiverAddresses, String subject, String text, List<File> attachments) {
        sendMail(receiverAddresses, subject, text, true, attachments, null, null);
    }

    public void sendHtmlMail(String[] receiverAddresses, String subject, String text) {
        sendMail(receiverAddresses, subject, text, true, null, null, null);
    }

    public void sendNonHtmlMail(String[] receiverAddresses, String subject, String text) {
        sendMail(receiverAddresses, subject, text, false, null, null, null);
    }

    public void sendNonHtmlMail(String[] receiverAddresses, String subject, String text, List<File> attachments) {
        sendMail(receiverAddresses, subject, text, false, attachments, null, null);
    }

    public void sendNonHtmlMail(String[] receiverAddresses, String subject, String text, List<File> attachments, String[] ccAddresses) {
        sendMail(receiverAddresses, subject, text, false, attachments, ccAddresses, null);
    }

    public void sendNonHtmlMail(String[] receiverAddresses, String subject, String text, List<File> attachments, String[] ccAddresses,
                                String[] bccAddresses) {
        sendMail(receiverAddresses, subject, text, false, attachments, ccAddresses, bccAddresses);
    }
}
