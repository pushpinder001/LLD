package com.pushpinder.service;

import com.pushpinder.exception.NoCarPresentForSlot;
import com.pushpinder.exception.ParkingLotFullException;
import com.pushpinder.exception.ParkingLotServiceAlreadyCreatedException;
import com.pushpinder.exception.ParkingStrategyNotFoundException;
import com.pushpinder.model.Car;
import com.pushpinder.model.Ticket;
import com.pushpinder.model.parking.stragegy.ParkingStrategyFactory;

import java.util.List;

public interface ParkingLotService {
    boolean createParkingLot(int capacity, ParkingStrategyFactory.ParkingStrategyType parkingStrategyType) throws ParkingLotServiceAlreadyCreatedException, ParkingStrategyNotFoundException;

    Ticket park(Car car) throws ParkingLotFullException;

    boolean unPark(Integer slotNo) throws NoCarPresentForSlot;

//    List<String> getRegistrationNoForCarColor(String color);
//
//    int slotNoForCar(String registrationNo);
//
//    List<Integer> getSlotNoOfCarColor(String color);

}
