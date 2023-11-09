package com.example.s3rekognition;

import java.util.List;

/**
 * This is the response sent back from the Apprunner service
 *
 */
public class PPEResponse {

    String bucketName;
    List<PPEClassificationResponse> results;

    public PPEResponse(String bucketName, List<PPEClassificationResponse> results) {
        this.bucketName = bucketName;
        this.results = results;
    }

    public String getBucketName() {
        return bucketName;
    }

    public List<PPEClassificationResponse> getResults() {
        return results;
    }
}

