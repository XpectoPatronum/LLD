package org.example.realSystems.snakeAndLadder.factory;

import org.example.realSystems.snakeAndLadder.enums.ObstacleType;
import org.example.realSystems.snakeAndLadder.model.LadderObstacle;
import org.example.realSystems.snakeAndLadder.model.Obstacle;
import org.example.realSystems.snakeAndLadder.model.SnakeObstacle;

public class ObstacleFactory {
    public static Obstacle createObstacle(String id, int start, int end, ObstacleType type) {
        return switch (type) {
            case SNAKE ->  new SnakeObstacle(id, start, end);
            case LADDER ->  new LadderObstacle(id, start, end);
            default -> throw new IllegalArgumentException("Invalid obstacle type");
        };
    }
}
