package com.example.gameui.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

@Service
@RequiredArgsConstructor
@Slf4j
public class AudioService {

    private final AudioFormatService formatService;

    @SneakyThrows
    public Flux<byte[]> getByteArrayFlux() {
        final AudioFormat audioFormat = formatService.getAudioFormat();
        final DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
        final TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
        targetDataLine.open(audioFormat);
        targetDataLine.start();
        byte[] tempBuffer = new byte[10000];
        return Flux.<byte[]>generate(sink -> {
            targetDataLine.read(tempBuffer, 0, tempBuffer.length);
            sink.next(tempBuffer);
        });
    }
}
