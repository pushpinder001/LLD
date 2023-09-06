package com.pushpinder.model;

import com.pushpinder.exception.CarAlreadyParkedException;
import com.pushpinder.exception.NoCarPresentForSlot;
import com.pushpinder.exception.ParkingLotFullException;
import com.pushpinder.exception.ParkingStrategyNotFoundException;
import com.pushpinder.model.parking.stragegy.ParkingStrategy;
import com.pushpinder.model.parking.stragegy.ParkingStrategyFactory;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    List<Slot> slots;

    int capacity;

    ParkingStrategy parkingStrategy;

    public ParkingLot(int capacity, ParkingStrategyFactory.ParkingStrategyType parkingStrategyType) throws ParkingStrategyNotFoundException {
        this.slots = new ArrayList<>();
        this.capacity = capacity;
        for(int i=1; i<=capacity; i++) {
            this.slots.add(new Slot(i));
        }
        this.parkingStrategy = ParkingStrategyFactory.getParkingStrategy(parkingStrategyType, this);
    }

    public Ticket park(Car car) throws ParkingLotFullException {
        if(slots.size()==0) {
            throw new ParkingLotFullException();
        }
        Slot slot = this.parkingStrategy.removeParkingSlot();

        try {
            slot.addCar(car);
        } catch (CarAlreadyParkedException e) {
            throw new RuntimeException(e);
        }

        return new Ticket(car.registerationNo, car.color, slot.getId());
    }

    public boolean unPark(Integer id) throws NoCarPresentForSlot {
        Slot s = this.slots.get(id-1);
        s.removeCar();
        return this.parkingStrategy.addParkingSlot(this.slots.get(id-1));
    }

    public List<Slot> getSlots() {
        return slots;
    }
}
