package com.example.gameui;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ClientUiApplication {

    public static void main(String[] args) {
        Application.launch(ClientFXApplication.class, args);
    }

}
