package org.example.realSystems.snakeAndLadder.service;

import org.example.realSystems.snakeAndLadder.enums.PlayerStatus;
import org.example.realSystems.snakeAndLadder.model.Board;
import org.example.realSystems.snakeAndLadder.model.Player;

public class OrchestratorService {

    private final DiceService diceService;

    public OrchestratorService(DiceService diceService) {
        this.diceService = diceService;
    }

    public void playerChance(Player player, Board board) {
        int chance = diceService.rollAll();
        System.out.println("Dice rolled by player " + player.getPlayerName() + ", Result: " + chance);
        int currPosition = player.getCurrentCell();
        if(currPosition + chance > board.getSize()) {
            System.out.println("Invalid move, chance led to outside the board, Current position: " + currPosition);
        }
        else if(currPosition + chance == board.getSize()) {
            System.out.println(player.getPlayerName() + " Player Won!");
            player.setCurrentCell(currPosition + chance);
            player.setPlayerStatus(PlayerStatus.WON);
        }else{
            int nextPosition = currPosition + chance;
            System.out.println("New position of " + player.getPlayerName() + " is: " + nextPosition);
            while(board.hasObstacle(nextPosition)){
                nextPosition = board.getObstacle(nextPosition).process();
            }
            player.setCurrentCell(nextPosition);
        }
    }

}
