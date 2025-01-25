package com.project.app;

import lombok.SneakyThrows;

public class Even implements Runnable{
    private State curState;
    private int even = 2;
    private int maxValue;

    public Even(State curState, int maxValue) {
        this.curState = curState;
        this.maxValue = maxValue;
    }

    @SneakyThrows
    @Override
    public void run() {
        while(even <= maxValue) {
            synchronized (curState) {
                //System.out.println("Even sync block started");
                if (curState.turn == Turn.ODD) {
                    //System.out.println("Even thread started waiting");
                    curState.wait();
                    //System.out.println("Even thread wokeup from waiting");
                }
                System.out.println(even);
                even += 2;
                curState.turn = Turn.ODD;
                curState.notifyAll();
            }
        }
    }
}
