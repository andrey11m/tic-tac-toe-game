package com.example.gameui.configuration;

import com.example.gameui.ClientFXApplication.StageReadyEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StageInitializer implements ApplicationListener<StageReadyEvent> {
    @Value("classpath:/chart.fxml")
    private Resource charResource;

    @Value("${spring.application.ui.title}")
    private String applicationTitle;
    private final ApplicationContext applicationContext;

    @Override
    @SneakyThrows
    public void onApplicationEvent(StageReadyEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(charResource.getURL());
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        Parent parent = fxmlLoader.load();
        Stage stage = event.getStage();
        stage.setTitle(applicationTitle);
        stage.setScene(new Scene(parent, 800, 600));
        stage.show();
    }
}
