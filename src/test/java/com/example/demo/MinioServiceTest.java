package com.example.demo;
import com.example.demo.service.MinioService;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URL;

class MinioServiceTest {

    @Test
    void testGeneratePresignedUrl() throws Exception {
        // Criar o serviço com os parâmetros que o construtor exige
        MinioService service = new MinioService(
                "http://localhost:9000",   // endpoint
                "minioadmin",              // accessKey
                "minioadmin",              // secretKey
                "albums"                   // bucket
        );

        // Executar o método
        URL url = service.generatePresignedUrl("album-1-Estudar.jpg");

        // Verificar se retornou algo
        assertNotNull(url);
    }
}
