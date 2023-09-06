package com.pushpinder.exception;

public class CarAlreadyParkedException extends Exception{
    public CarAlreadyParkedException() {
        super("Car is already parked");
    }
}
