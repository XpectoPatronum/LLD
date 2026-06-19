package org.example.realSystems.snakeAndLadder.model;

import org.example.realSystems.snakeAndLadder.enums.ObstacleType;

public class SnakeObstacle extends Obstacle {

    public SnakeObstacle(String id, int startCell, int endCell) {
        super(id, ObstacleType.SNAKE, startCell, endCell);
    }

    @Override
    public ObstacleType getType() {
        return ObstacleType.SNAKE;
    }

    @Override
    public int process() {
        System.out.println("Encountered snake ...");
        System.out.println("Moving from " + getStartCell() + " to " + getEndCell());
        return getEndCell();
    }
}
