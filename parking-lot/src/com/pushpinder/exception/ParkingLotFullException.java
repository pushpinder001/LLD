package com.pushpinder.exception;

public class ParkingLotFullException extends Exception{
    public ParkingLotFullException() {
        super("Parking Lot is Full");
    }
}
