package com.example.s3rekognition;

import java.io.Serializable;

public class PPEClassificationResponse  implements Serializable  {

    private String fileName;
    private boolean violation;

    public PPEClassificationResponse() {
    }

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

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setViolation(boolean violation) {
        this.violation = violation;
    }
}
