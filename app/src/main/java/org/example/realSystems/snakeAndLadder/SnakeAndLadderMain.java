package org.example.realSystems.snakeAndLadder;

import org.example.realSystems.snakeAndLadder.enums.ObstacleType;
import org.example.realSystems.snakeAndLadder.factory.ObstacleFactory;
import org.example.realSystems.snakeAndLadder.model.*;
import org.example.realSystems.snakeAndLadder.service.DiceService;
import org.example.realSystems.snakeAndLadder.service.OrchestratorService;

import java.util.List;

public class SnakeAndLadderMain {
    public static void main(String[] args) {
        Board board = new Board("Board-1",25);

        Obstacle ladder1 = ObstacleFactory.createObstacle("ladder1",3,18, ObstacleType.LADDER);
        Obstacle ladder2 = ObstacleFactory.createObstacle("ladder2",7,14,ObstacleType.LADDER);

        Obstacle snake1 = ObstacleFactory.createObstacle("snake1", 13,2,ObstacleType.SNAKE);
        Obstacle snake2 = ObstacleFactory.createObstacle("snake2", 23,5,ObstacleType.SNAKE);

        board.addObstacle(ladder1);
        board.addObstacle(ladder2);
        board.addObstacle(snake1);
        board.addObstacle(snake2);

        Player player1 = new Player("Alice","p1");
        Player player2 = new Player("Bob","p2");

        OrchestratorService orchestratorService = new OrchestratorService(new DiceService(1));

        Game game = new Game(board, List.of(player1,player2),orchestratorService);
        game.setBoard();
        game.playGame();
        game.printBoardStatus();
    }
}
