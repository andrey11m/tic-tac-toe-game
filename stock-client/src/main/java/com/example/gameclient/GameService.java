package com.example.gameclient;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class GameService {

    private final WebClient webClient;

    @Value("${spring.application.client.url}")
    private String url;

    public void setSymbol(int id) {
        webClient.get()
                .uri(url+"set-symbol/" + id)
                .retrieve().toEntity(String.class).block();
    }

    public void restartGame() {
        webClient.get()
                .uri(url+ "restart")
                .retrieve().toEntity(String.class).block();
    }



}
