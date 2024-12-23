package com.example.UploadImages.Utils;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
public class UploadImage {

    private final MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

    public UploadImage(MinioClient minioClient ){
        this.minioClient = minioClient;

    }

    public boolean uploadImage(MultipartFile multipartFile){

        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            System.out.println(found);
            if (!found){
                System.out.println("Belum ada bucket");
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("vehicle").build());
            }else {
                System.out.println("Bucket Vehicle already exsist");
            }
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(multipartFile.getOriginalFilename())
                            .stream(multipartFile.getInputStream()
                                    ,multipartFile.getSize() ,-1)
                            .contentType(multipartFile.getContentType())
                            .build()
            );

            return true;
        }catch (MinioException | IOException | InvalidKeyException | NoSuchAlgorithmException e){

            return false;
        }

    }
}
