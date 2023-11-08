package com.example.s3rekognition.controller;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RekognitionController {

    private final AmazonS3 s3Client;
    private final AmazonRekognition rekognitionClient;

    public RekognitionController() {
        this.s3Client =  AmazonS3ClientBuilder.standard().build();;
        this.rekognitionClient = AmazonRekognitionClientBuilder.standard().build() ;
    }

    @GetMapping("/scan-ppe")
    public List<DetectProtectiveEquipmentResult> scanForPPE(@RequestParam String bucketName) {
        List<DetectProtectiveEquipmentResult> results = new ArrayList<>();

        // List all objects in the S3 bucket
        ListObjectsV2Result listing = s3Client.listObjectsV2(bucketName);
        List<S3ObjectSummary> summaries = listing.getObjectSummaries();

        // Iterate over each object and scan for PPE
        for (S3ObjectSummary summary : summaries) {
            DetectProtectiveEquipmentRequest request = new DetectProtectiveEquipmentRequest()
                    .withImage(new Image()
                            .withS3Object(new S3Object()
                                    .withBucket(bucketName)
                                    .withName(summary.getKey())))
                    .withSummarizationAttributes(new ProtectiveEquipmentSummarizationAttributes()
                            .withMinConfidence(80f)
                            .withRequiredEquipmentTypes("FACE_COVER"));

            DetectProtectiveEquipmentResult result = rekognitionClient.detectProtectiveEquipment(request);
            results.add(result);
        }

        return results;
    }
}
