package org.example.realSystems.snakeAndLadder.service;

import org.example.realSystems.snakeAndLadder.enums.PlayerStatus;
import org.example.realSystems.snakeAndLadder.model.Board;
import org.example.realSystems.snakeAndLadder.model.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Board board;
    private Integer currentActivePlayers;
    private final List<Player> players = new ArrayList<>();
    private final OrchestratorService orchestratorService;

    public Game(Board board, List<Player> players, OrchestratorService orchestratorService) {
        this.board = board;
        this.players.addAll(players);
        currentActivePlayers = players.size();
        this.orchestratorService = orchestratorService;
    }

    public void printBoardStatus() {
        for(Player player : players) {
            System.out.println(player.getPlayerName() + " | " + player.getCurrentCell() + " | " + player.getPlayerStatus());
        }
    }

    public void setBoard() {
        int startingCell = 1;
        players.forEach(player -> {
            player.setCurrentCell(startingCell);
            player.setPlayerStatus(PlayerStatus.PLAYING);
        });
    }

    public void playGame() {
        while (currentActivePlayers > 1) {

            for (Player player : players) {
                if (player.getPlayerStatus() == PlayerStatus.PLAYING) {
                    System.out.println("***************************");
                    System.out.println(player.getPlayerName() + "'s Chance");

                    orchestratorService.playerChance(player, board);

                    if (player.getPlayerStatus() == PlayerStatus.WON) {
                        currentActivePlayers--;

                        if (currentActivePlayers == 1) {
                            System.out.println("Game over! Only one player left.");
                            markLastPlayerAsLost();
                            return;
                        }
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("------------------------");
                }
            }
        }
    }

    private void markLastPlayerAsLost() {
        for (Player player : players) {
            if (player.getPlayerStatus() == PlayerStatus.PLAYING) {
                player.setPlayerStatus(PlayerStatus.LOST);
                break;
            }
        }
    }
}
