package com.project.app;

import lombok.SneakyThrows;

public class Odd implements Runnable{
    private final int max;
    private State curState;
    private int odd = 1;


    public Odd(State curState, int max) {
        this.curState = curState;
        this.max = max;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (odd <= max) {
            synchronized (curState) {
                //System.out.println("Odd sync block started");
                if(curState.turn != Turn.ODD) {
                    curState.wait();
                }
                System.out.println(odd);
                odd += 2;
                curState.turn = Turn.EVEN;
                curState.notifyAll();
            }
        }
    }
}
