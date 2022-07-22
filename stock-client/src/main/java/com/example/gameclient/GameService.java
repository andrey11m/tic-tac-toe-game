package com.example.gameclient;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class GameService {

    private final WebClient webClient;

    public void setSymbol(int id) {
        ResponseEntity<String> block = webClient.get()
                .uri("http://localhost:8080/set-symbol/" + id)
                .retrieve().toEntity(String.class).block();
        System.out.println(block.getBody());
    }

}
