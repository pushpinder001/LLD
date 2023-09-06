package com.pushpinder.service.impl;

import com.pushpinder.exception.NoCarPresentForSlot;
import com.pushpinder.exception.ParkingLotFullException;
import com.pushpinder.exception.ParkingLotServiceAlreadyCreatedException;
import com.pushpinder.exception.ParkingStrategyNotFoundException;
import com.pushpinder.model.Car;
import com.pushpinder.model.ParkingLot;
import com.pushpinder.model.Slot;
import com.pushpinder.model.Ticket;
import com.pushpinder.model.parking.stragegy.ParkingStrategyFactory;
import com.pushpinder.service.ParkingLotService;

import java.util.*;
import java.util.stream.Collectors;

public class ParkingLotServiceImpl implements ParkingLotService {

    private ParkingLot parkingLot;

    public ParkingLotServiceImpl() {

    }

    public boolean createParkingLot(int capacity, ParkingStrategyFactory.ParkingStrategyType parkingStrategyType) throws ParkingLotServiceAlreadyCreatedException, ParkingStrategyNotFoundException {
        if(this.parkingLot != null) {
            throw new ParkingLotServiceAlreadyCreatedException();
        }

        this.parkingLot = new ParkingLot(capacity, parkingStrategyType);
        return true;
    }

    @Override
    public Ticket park(Car car) throws ParkingLotFullException {
        return this.parkingLot.park(car);
    }

    @Override
    public boolean unPark(Integer id) throws NoCarPresentForSlot {
        return this.parkingLot.unPark(id);
    }
}
