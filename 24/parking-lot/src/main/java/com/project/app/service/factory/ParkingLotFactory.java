package com.project.app.service.factory;

import com.project.app.entity.Slot;
import com.project.app.service.ParkingLotService;
import com.project.app.service.strategy.NearestSlotFinderStrategy;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotFactory {
    public static void createParkingLot(int noOfSlots) {
        List<Slot> slots = new ArrayList<>();
        for(int i=1; i<=noOfSlots; i++) {
            slots.add(new Slot(i));
        }

        ParkingLotService.createInstance(slots, new NearestSlotFinderStrategy());
    }
}
