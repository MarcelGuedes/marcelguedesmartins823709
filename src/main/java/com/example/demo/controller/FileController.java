package com.example.demo.controller;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.http.Method;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/files")
public class FileController {

    private final MinioClient minioClient;

    public FileController() {
        this.minioClient = MinioClient.builder()
                .endpoint("http://localhost:9000") // endpoint do MinIO
                .credentials("admin", "admin123")  // credenciais definidas no docker run
                .build();
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // Faz upload para o bucket "albums"
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket("albums")
                            .object(file.getOriginalFilename())
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );

            // Gera um presigned URL válido por 1 hora
            String url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket("albums")
                            .object(file.getOriginalFilename())
                            .expiry(60 * 60) // expira em 1 hora
                            .build()
            );

            return ResponseEntity.ok("Arquivo disponível em: " + url);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao fazer upload: " + e.getMessage());
        }
    }
}
