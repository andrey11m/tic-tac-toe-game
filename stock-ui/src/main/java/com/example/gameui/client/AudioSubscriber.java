package com.example.gameui.client;

import lombok.SneakyThrows;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import java.util.Arrays;
import java.util.function.Consumer;


public class AudioSubscriber implements Consumer<byte[]> {

    AudioFormat audioFormat;
    DataLine.Info dataLineInfo;
    SourceDataLine sourceDataLine;

    @SneakyThrows
    public AudioSubscriber() {
        audioFormat = getAudioFormat();
        dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
        sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
        sourceDataLine.open(audioFormat);
        sourceDataLine.start();
    }

    @Override
    public void accept(byte[] bytes) {
        System.out.println(Arrays.toString(bytes));
        byte[] temp;
        if (bytes.length % 2 != 0) {
            temp = Arrays.copyOf(bytes, bytes.length +1);
            sourceDataLine.write(temp, 0, temp.length);
        } else {
            sourceDataLine.write(bytes, 0, bytes.length);
        }

        sourceDataLine.drain();
    }

    private AudioFormat getAudioFormat() {
        float sampleRate = 8000.0F;
        //8000,11025,16000,22050,44100
        int sampleSizeInBits = 16;
        //8,16
        int channels = 1;
        //1,2
        boolean signed = true;
        //true,false
        boolean bigEndian = false;
        //true,false
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
    }
}
