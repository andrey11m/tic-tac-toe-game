package com.example.gameui.controller;

import com.example.gameui.service.CaptureAudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.io.ByteArrayOutputStream;

@RestController
@RequiredArgsConstructor
public class AudioController {

    private final CaptureAudioService audioService;

    @GetMapping("/audio-stream")
    public Flux<byte[]> sendAudio() {
        return audioService.getByteArrayOutputStreamFlux();
    }
}
