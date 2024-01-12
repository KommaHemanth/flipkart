package com.insignia.config;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class AwsS3Bean {
    //TODO: need to read from secrets
    @Value("AKIA3WJJAU2DSMY47WGN")
    private String accessKey;
    //TODO: need to read from secrets
    @Value("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")
    private String secretKey;
    //TODO: need to read from secrets
    @Value("ap-south-1")
    private String region;
    //TODO: need to read from secrets
    @Value("test-bucket-for-document")
    private String bucketName;

    @Bean
    public AmazonS3 s3() {
        AWSCredentials awsCredentials =
                new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
