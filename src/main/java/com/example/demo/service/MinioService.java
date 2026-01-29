package com.example.demo.service;



import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Service
public class MinioService {

    private final MinioClient minioClient;
    private final String bucket;

    public MinioService(
            @Value("${minio.endpoint}") String endpoint,
            @Value("${minio.access-key}") String accessKey,
            @Value("${minio.secret-key}") String secretKey,
            @Value("${minio.bucket}") String bucket) {

        this.bucket = bucket;
        this.minioClient = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }

    // Upload de arquivo
    public void uploadFile(String fileName, byte[] content) throws Exception {
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucket)
                        .object(fileName)
                        .stream(new ByteArrayInputStream(content), content.length, -1)
                        .contentType("image/jpeg") // ajuste conforme o tipo real
                        .build()
        );
    }

    // Geração de URL pré-assinada
    public URL generatePresignedUrl(String fileName) throws Exception {
        String url = minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucket)
                        .object(fileName)
                        .expiry(30, TimeUnit.MINUTES)
                        .build()
        );
        return new URL(url);
    }
}
