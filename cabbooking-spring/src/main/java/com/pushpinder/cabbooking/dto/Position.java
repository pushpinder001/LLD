package com.pushpinder.cabbooking.dto;

public record Position(int X, int Y) {

    public int dist(Position p) {
        return (int)Math.sqrt(Math.pow(X - p.X, 2) + Math.pow(Y- p.Y, 2));
    }
}
