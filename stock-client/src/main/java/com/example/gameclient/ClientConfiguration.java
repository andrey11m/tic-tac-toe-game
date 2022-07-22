package com.example.gameclient;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfiguration {
    
    @Bean
    public WebClientGameClient webClientGameClient(WebClient webClient){
        return new WebClientGameClient(webClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public WebClient webClient(){
        return WebClient.builder().build();
    }
    @Bean
    public GameService gameService(){
        return new GameService(webClient());
    }

    
}
