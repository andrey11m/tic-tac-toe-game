package com.example.gameui.controller;

import com.example.gameui.service.AudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class AudioController {

    private final AudioService audioService;

    @GetMapping("/audio-stream")
    public Flux<byte[]> sendAudio() {
        return audioService.getByteArrayFlux();
    }
}
