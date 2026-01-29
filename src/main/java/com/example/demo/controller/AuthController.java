package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.TokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.security.JwtUtil;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if ("marcel".equals(request.getUsername()) && "123456".equals(request.getPassword())) {
            String token = JwtUtil.generateToken(request.getUsername());
            return ResponseEntity.ok(new TokenResponse(token));
        }
        return ResponseEntity.status(401).body("Credenciais inválidas");
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestHeader("Authorization") String header) {
        if (header != null && header.startsWith("Bearer ")) {
            String oldToken = header.substring(7);
            String username = JwtUtil.validateToken(oldToken);

            if (username != null && JwtUtil.isTokenExpired(oldToken)) {
                String newToken = JwtUtil.generateToken(username);
                return ResponseEntity.ok(new TokenResponse(newToken));
            }
        }
        return ResponseEntity.status(401).body("Token inválido ou ainda válido");
    }
}
