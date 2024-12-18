package com.project.service;

import com.project.type.GameState;
import lombok.NonNull;

import java.util.*;

public class Game {
    List<IPlayer> playerList;
    int turn;
    Board board;
    Dice dice;
    Map<IPlayer, int[]> playerToCurPos;
    private GameState gameState;

    public Game(@NonNull IPlayer[] players,@NonNull Board board,@NonNull Dice dice, IPlayer startPlayer) {
        //ADD validation

        playerToCurPos = new HashMap<>();

        turn = 0;
        playerList = new ArrayList<>();
        for(int i=0; i<players.length; i++) {
            playerList.add(players[i]);
            playerToCurPos.put(players[i], board.getStartPosition());
            if(players[i] == startPlayer) {
                turn = i;
            }
        }

        this.board = board;
        this.dice = dice;

        gameState = GameState.NOT_STARTED;
    }

    public void startGame() {
        if(gameState == GameState.NOT_STARTED) {
            gameState = GameState.STARTED;
            playGame();
        }
    }

    public void rollDice(IPlayer player) {
        if(playerList.get(turn) == player) {
            int rollVal = dice.roll();
            System.out.println("The dice rolled out with value " + rollVal );
            int[] curPosition = playerToCurPos.get(player);
            int[] targetPosition = board.getTargetBoardPositionForMove(curPosition, rollVal);
            System.out.println("Cur position of player " + player.getPlayerId()+  " " + Arrays.toString(curPosition) );
            System.out.println("Target position of player " + player.getPlayerId()+  " "  + Arrays.toString(targetPosition) );

            //Check for win case
            if(board.isThisEndOfBoard(targetPosition)) {
                System.out.println("Player player " + player.getPlayerId() + "has won");
                gameState = GameState.FINISHED;
            } else {
                playerToCurPos.put(player, curPosition);
                turn = (turn+1)%playerList.size();
                playGame();
            }
        } else {
            System.out.println("Not your turn");
            //Throw exception
        }
    }

    private void playGame() {
        //Get player
        IPlayer player = playerList.get(turn); //OCP Non compliant
        player.play();
    }
}
