package com.example.gameclient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class WebClientGameClient {
    private final WebClient webClient;

    public Flux<Game> pricesFor() {
        return webClient
                .get()
                .uri("http://localhost:8080/game")
                .retrieve()
                .bodyToFlux(Game.class)
                .retry().doOnError(IOException.class, e -> log.error(e.getMessage()));
    }
}
