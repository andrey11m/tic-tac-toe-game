package com.example.gameui;

import com.example.gameclient.Game;
import com.example.gameclient.GameService;
import com.example.gameclient.WebClientGameClient;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class ChartController implements Consumer<Game> {
    private final WebClientGameClient webClientGameClient;
    private final GameService gameService;

    @FXML
    public Label secondPlayer;
    @FXML
    public Label firstPlayer;

    @FXML
    public Button button1;
    @FXML
    public Button button2;
    @FXML
    public Button button3;
    @FXML
    public Button button4;
    @FXML
    public Button button5;
    @FXML
    public Button button6;
    @FXML
    public Button button7;
    @FXML
    public Button button8;
    @FXML
    public Button button9;
    public Button restart;
    public Text winner;

    @FXML
    @SneakyThrows
    public void initialize() {
        webClientGameClient.pricesFor().subscribe(this);
    }

    @Override
    public void accept(Game game) {
        Platform.runLater(() -> setFXInfo(game));
    }


    public void setFXInfo(Game game) {
        button1.setText(game.getGameArray()[0]);
        button2.setText(game.getGameArray()[1]);
        button3.setText(game.getGameArray()[2]);
        button4.setText(game.getGameArray()[3]);
        button5.setText(game.getGameArray()[4]);
        button6.setText(game.getGameArray()[5]);
        button7.setText(game.getGameArray()[6]);
        button8.setText(game.getGameArray()[7]);
        button9.setText(game.getGameArray()[8]);
        firstPlayer.setText(game.getFirstPlayer());
        secondPlayer.setText(game.getSecondPlayer());
        winner.setText("Winner " + game.getWinner());
    }

    public void setButton1(ActionEvent actionEvent) {
        gameService.setSymbol(0);
    }
    public void setButton2(ActionEvent actionEvent) {
        gameService.setSymbol(1);
    }
    public void setButton3(ActionEvent actionEvent) {
        gameService.setSymbol(2);
    }
    public void setButton4(ActionEvent actionEvent) {
        gameService.setSymbol(3);
    }
    public void setButton5(ActionEvent actionEvent) {
        gameService.setSymbol(4);
    }
    public void setButton6(ActionEvent actionEvent) {
        gameService.setSymbol(5);
    }
    public void setButton7(ActionEvent actionEvent) {
        gameService.setSymbol(6);
    }
    public void setButton8(ActionEvent actionEvent) {
        gameService.setSymbol(7);
    }
    public void setButton9(ActionEvent actionEvent) {
        gameService.setSymbol(8);
    }

    public void restartGame(ActionEvent actionEvent) {
        gameService.restartGame();
    }
}
