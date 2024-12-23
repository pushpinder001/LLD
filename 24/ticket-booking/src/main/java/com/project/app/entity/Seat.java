package com.project.app.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Seat {
    final char row;

    final int seatId;

    public String toString() {
        return "" + row + seatId;
    }
}
