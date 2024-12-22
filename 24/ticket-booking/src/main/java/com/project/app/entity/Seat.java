package com.project.app.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Seat {
    char row;

    int seatId;

    public String toString() {
        return "" + row + seatId;
    }
}
