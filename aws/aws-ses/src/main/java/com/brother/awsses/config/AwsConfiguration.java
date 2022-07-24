package com.brother.awsses.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfiguration {
    @Value("${aws.access.key}")
    private String awsAccessKey;
    @Value("${aws.secret.key}")
    private String awsSecretKey;
    @Value("${aws.region}")
    private String region;


    public AWSStaticCredentialsProvider awsCredentials() {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey));
    }

    @Bean
    AmazonSimpleEmailService getAmazonSimpleEmailService() {
        return AmazonSimpleEmailServiceClientBuilder.standard().withRegion(region)
                .withCredentials(awsCredentials())
                .build();
    }
}
