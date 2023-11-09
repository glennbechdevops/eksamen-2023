package com.example.s3rekognition;

public class PPEClassificationResponse {

    private String fileName;
    private boolean violation;

    public PPEClassificationResponse(String fileName, boolean violation) {
        this.fileName = fileName;
        this.violation = violation;
    }

    public String getFileName() {
        return fileName;
    }

    public boolean isViolation() {
        return violation;
    }
}
