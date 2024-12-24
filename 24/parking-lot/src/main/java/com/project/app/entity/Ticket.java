package com.project.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Ticket {
    private final String id;
    private final String regNo;
    private final String color;
    private final int slotNo;

    @Override
    public String toString() {
        return
                regNo + '\'' +
                color + '\'';
    }
}
