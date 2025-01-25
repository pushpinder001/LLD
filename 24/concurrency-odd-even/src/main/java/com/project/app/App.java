package com.project.app;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        State curState = new State(Turn.ODD);

        Thread thread1 = new Thread(new Even(curState, 100));
        Thread thread2 = new Thread((new Odd(curState, 100)));

        thread1.start();
        thread2.start();
    }
}
