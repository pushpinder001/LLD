package com.project.service;

import lombok.Getter;

public abstract class IPlayer {
    int id;

    public IPlayer(int id) {
        this.id = id;
    }

    protected Game game;

    public int getPlayerId() {
        return id;
    }

    public void setGame(Game game) {
        this.game = game;
    }


    public abstract void play();
}
