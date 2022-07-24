package com.example.gameui.service;

import org.springframework.stereotype.Service;

import javax.sound.sampled.AudioFormat;

@Service
public class AudioFormatService {

    public AudioFormat getAudioFormat(){
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
