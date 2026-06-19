package org.example.realSystems.snakeAndLadder.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public class Dice {
    private int value;

    public int roll(){
        ArrayList<Integer> possibles = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        Random random = new Random();
        return possibles.get(random.nextInt(possibles.size()));
    }
}
