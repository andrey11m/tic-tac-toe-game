package com.example.gameui.client;

import com.example.gameui.model.Game;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebClientGameClient {
    private final WebClient webClient;
    @Value("${spring.application.client.url}")
    private String url;

    public Flux<Game> startGame() {
        return webClient
                .get()
                .uri(url+ "game")
                .retrieve()
                .bodyToFlux(Game.class)
                .retry().doOnError(IOException.class, e -> log.error(e.getMessage()));
    }
}
