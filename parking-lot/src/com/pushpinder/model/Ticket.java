package com.pushpinder.model;

public class Ticket {
    public String registraionNo;

    public String color;

    public Integer slotNo;

    public Ticket(String registraionNo, String color, Integer slotNo) {
        this.registraionNo = registraionNo;
        this.color = color;
        this.slotNo = slotNo;
    }
}
