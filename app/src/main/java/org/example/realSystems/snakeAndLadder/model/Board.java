package org.example.realSystems.snakeAndLadder.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Board {
    private final String id;
    private final int size;
    private Map<Integer, Obstacle> obstacles = new HashMap<>();;

    public Board(String id, int size) {
        this.id = id;
        this.size = size;
    }

    public Obstacle getObstacle(int cell) {
        return obstacles.get(cell);
    }

    public boolean hasObstacle(int cell) {
        return obstacles.containsKey(cell);
    }

    public void addObstacle(Obstacle obstacle) {
        if(obstacles.containsKey(obstacle.getStartCell())) {
            System.out.println("This start already has an obstacle " + obstacle.getStartCell());
            throw new IllegalArgumentException("This start already has an obstacle " + obstacle.getStartCell());
        }
        obstacles.put(obstacle.getStartCell(), obstacle);
    }

    public void removeObstacle(Obstacle obstacle) {
        obstacles.remove(obstacle.getStartCell());
    }

}
