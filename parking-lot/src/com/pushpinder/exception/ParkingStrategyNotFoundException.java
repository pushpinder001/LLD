package com.pushpinder.exception;

public class ParkingStrategyNotFoundException extends Exception {
    public ParkingStrategyNotFoundException() {
        super("Parking Strategy Not Found");
    }
}
