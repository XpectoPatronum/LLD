package org.example.realSystems.snakeAndLadder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.realSystems.snakeAndLadder.enums.ObstacleType;

@AllArgsConstructor
@Getter
public abstract class Obstacle {
    private final String id;
    private final ObstacleType type;
    private final int startCell;
    private final int endCell;

    public abstract ObstacleType getType();
    public abstract int process();
}
