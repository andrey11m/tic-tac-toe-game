package com.example.gameui.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import java.io.ByteArrayOutputStream;
import java.time.Duration;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class CaptureAudioService {

    private final AudioFormatService formatService;

    private TargetDataLine targetDataLine;
    private AudioFormat audioFormat;
    private boolean stopCapture = true;
    private int temp = 0;
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] tempBuffer;


    private void captureAudio(){
        try{
            //Установим все для захвата
            byteArrayOutputStream = new ByteArrayOutputStream();
            audioFormat = formatService.getAudioFormat();
            DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
            targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
            targetDataLine.open(audioFormat);
            targetDataLine.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public Flux<byte[]> getByteArrayOutputStreamFlux() {
        return Flux.interval(Duration.ofMillis(600)).map(aLong -> {
            System.out.println(Arrays.toString(tempBuffer));
            return tempBuffer;
        });
    }

    public void setStopCapture() {
        if (temp % 2 == 0) {
            stopCapture = true;
        } else {
            stopCapture = false;
        }
        temp++;
    }

    @Async
    public byte[] getByteArrayOutputStream() {
        captureAudio();
        tempBuffer = new byte[10000];
        try{
            while(stopCapture){
                int cnt = targetDataLine.read(tempBuffer, 0, tempBuffer.length);
                if(cnt > 0){
                    //Сохраняем данные в выходной поток
                    byteArrayOutputStream.write(tempBuffer, 0, cnt);
                }
            }
            byteArrayOutputStream.close();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return tempBuffer;
    }



}
