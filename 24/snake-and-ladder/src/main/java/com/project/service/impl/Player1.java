package com.project.service.impl;

import com.project.service.IPlayer;

public class Player1 extends IPlayer {

    public Player1(int id) {
        super(id);
    }

    @Override
    public void play() {
        System.out.println("This is player " + getPlayerId());
        game.rollDice(this);
    }
}
