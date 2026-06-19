package org.example.realSystems.snakeAndLadder.service;

import lombok.AllArgsConstructor;
import org.example.realSystems.snakeAndLadder.model.Dice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DiceService {
    int numberOfDice;
    List<Dice> diceList;

    public DiceService(int n) {
        this.numberOfDice = n;
        this.diceList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            diceList.add(new Dice());
        }
    }

    public int rollAll(){
        int totalRollAnswer = 0;
        for(Dice dice: diceList){
            totalRollAnswer += dice.roll();
        }
        return totalRollAnswer;
    }
}
