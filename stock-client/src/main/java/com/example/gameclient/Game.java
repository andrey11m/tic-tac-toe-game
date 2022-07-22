package com.example.gameclient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Game {
    private String firstPlayer;
    private String secondPlayer;
    private String[] gameArray;
    private String winner;
}
