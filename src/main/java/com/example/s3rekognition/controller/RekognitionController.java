package com.example.s3rekognition.controller;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.example.s3rekognition.PPEClassificationResponse;
import com.example.s3rekognition.PPEResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;


@RestController
public class RekognitionController {

    private final AmazonS3 s3Client;
    private final AmazonRekognition rekognitionClient;

    private static Logger logger = Logger.getLogger(RekognitionController.class.getName());

    public RekognitionController() {
        this.s3Client =  AmazonS3ClientBuilder.standard().build();;
        this.rekognitionClient = AmazonRekognitionClientBuilder.standard().build() ;
    }

    /**
     * This endpoint takes an S3 bucket name in as an argument, scans all the
     * Files in the bucket for Protective Gear Violations.
     *
     * It does so by iterating over all persons in a picture, and then again over
     * each body part of the person. If the body part is a FACE and there is no
     * protective gear on it, a violation is recorded for the picture.
     *
     * @param bucketName
     * @return
     */
    @GetMapping("/scan-ppe")
    public EntityResponse<PPEResponse> scanForPPE(@RequestParam String bucketName) {
        List<DetectProtectiveEquipmentResult> results = new ArrayList<>();


        List<PPEClassificationResponse> classificationResponses = new ArrayList<>();

        // List all objects in the S3 bucket
        ListObjectsV2Result listing = s3Client.listObjectsV2(bucketName);
        List<S3ObjectSummary> summaries = listing.getObjectSummaries();

        // Iterate over each object and scan for PPE
        for (S3ObjectSummary file : summaries) {
            logger.info("scanning "+ file.getKey());
            DetectProtectiveEquipmentRequest request = new DetectProtectiveEquipmentRequest()
                    .withImage(new Image()
                            .withS3Object(new S3Object()
                                    .withBucket(bucketName)
                                    .withName(file.getKey())))
                     .withSummarizationAttributes(new ProtectiveEquipmentSummarizationAttributes()
                            .withMinConfidence(80f)
                            .withRequiredEquipmentTypes("FACE_COVER"));

            DetectProtectiveEquipmentResult result = rekognitionClient.detectProtectiveEquipment(request);

            // If any person on an image lacks PPE on the face, it's a violation of regulations
            boolean violation = result.getPersons().stream()
                    .flatMap(p -> p.getBodyParts().stream())
                    .anyMatch(bodyPart -> bodyPart.getName().equals("FACE") && bodyPart.getEquipmentDetections().isEmpty());

            PPEClassificationResponse classification = new PPEClassificationResponse(file.getKey(), violation);
            classificationResponses.add(classification);
        }
        PPEResponse ppeResponse = new PPEResponse(bucketName, classificationResponses);
        return EntityResponse.<PPEResponse> fromObject(ppeResponse).status(200).build();
    }
}
