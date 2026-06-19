package org.example.realSystems.snakeAndLadder.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.realSystems.snakeAndLadder.enums.PlayerStatus;

@RequiredArgsConstructor
@Data
public class Player {
    private final String playerName;
    private final String playerId;
    private PlayerStatus playerStatus;
    private int currentCell;
}
