package com.pushpinder.model;

public class Car {
    public String color;

    public String registerationNo;

    public Car(String color, String registerationNo) {
        this.color = color;
        this.registerationNo = registerationNo;
    }

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", registerationNo='" + registerationNo + '\'' +
                '}';
    }
}
