package com.pushpinder.exception;

public class ParkingLotServiceAlreadyCreatedException extends Exception{
    public ParkingLotServiceAlreadyCreatedException() {
        super("Parking lot is already created");
    }
}
