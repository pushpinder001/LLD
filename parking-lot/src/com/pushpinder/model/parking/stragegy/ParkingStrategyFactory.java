package com.pushpinder.model.parking.stragegy;

import com.pushpinder.exception.ParkingStrategyNotFoundException;
import com.pushpinder.model.ParkingLot;

public final class ParkingStrategyFactory {

    public enum ParkingStrategyType {
        NATURAL_ORDER;
    }
    public static ParkingStrategy getParkingStrategy(ParkingStrategyType parkingStrategyType, ParkingLot parkingLot) throws ParkingStrategyNotFoundException {
        return switch(parkingStrategyType) {
            case NATURAL_ORDER -> new NaturalOrderParkingStrategy(parkingLot);
            default -> throw new ParkingStrategyNotFoundException();
        };
    }
}
