package com.example.demo.config;


import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

    // cache para guardar os "baldes" por usuário/IP
    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    private Bucket createNewBucket() {
        // até 10 requisições por minuto
        Bandwidth limit = Bandwidth.classic(10, Refill.greedy(10, Duration.ofMinutes(1)));
        return Bucket.builder().addLimit(limit).build();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String user = request.getRemoteAddr(); // aqui usamos o IP como identificador
        Bucket bucket = cache.computeIfAbsent(user, k -> createNewBucket());

        if (bucket.tryConsume(1)) {
            // deixa passar a requisição
            filterChain.doFilter(request, response);
        } else {
            // bloqueia se passar do limite
            response.setStatus(429); // Too Many Requests
            response.getWriter().write("Rate limit excedido: máximo 10 requisições por minuto");
        }
    }
}
