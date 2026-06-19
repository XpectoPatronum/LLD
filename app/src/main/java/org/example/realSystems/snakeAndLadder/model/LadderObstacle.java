package org.example.realSystems.snakeAndLadder.model;

import org.example.realSystems.snakeAndLadder.enums.ObstacleType;

public class LadderObstacle extends Obstacle {

    public LadderObstacle(String id, int startCell, int endCell) {
        super(id, ObstacleType.LADDER, startCell, endCell);
    }

    @Override
    public ObstacleType getType() {
        return ObstacleType.LADDER;
    }

    @Override
    public int process() {
        System.out.println("Encountered ladder ...");
        System.out.println("Moving from " + getStartCell() + " to " + getEndCell());
        return getEndCell();
    }
}
