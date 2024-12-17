package com.project.service;

import java.util.Random;

public class Dice {
    private final int noOfFaces;
    private final Random random;

    public Dice(int noOfFaces) {
        this.noOfFaces = noOfFaces;
        this.random = new Random();
    }

    public int roll() {
        return random.nextInt(1, noOfFaces+1);
    }
}
