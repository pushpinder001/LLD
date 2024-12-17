package com.project.app;

import com.project.service.Board;
import com.project.service.Dice;
import com.project.service.Game;
import com.project.service.IPlayer;
import com.project.service.factory.BoardSimpleFactory;
import com.project.service.impl.Player1;
import com.project.service.impl.Player2;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Player1 player1 = new Player1(1);
        Player2 player2 = new Player2(2);

        Board board = BoardSimpleFactory.getSimpleBoardObject();
        Dice dice = new Dice(5);

        Game game = new Game(new IPlayer[]{player1, player2}, board, dice, player1);
        player1.setGame(game);
        player2.setGame(game);

        game.startGame();
    }
}
