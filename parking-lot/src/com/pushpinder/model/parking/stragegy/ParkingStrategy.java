package com.pushpinder.model.parking.stragegy;

import com.pushpinder.model.ParkingLot;
import com.pushpinder.model.Slot;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public abstract class ParkingStrategy {
    private Queue<Slot> minPriorityQueue;

    public ParkingStrategy(ParkingLot parkingLot) {
        this.minPriorityQueue = new PriorityQueue<>(Comparator.comparingInt(slot -> slot.getId()));
        for(var slot : parkingLot.getSlots()) {
            minPriorityQueue.add(slot);
        }
    }

    public Slot removeParkingSlot() {
        return minPriorityQueue.poll();
    }

    public boolean addParkingSlot(Slot slot) {
        minPriorityQueue.add(slot);
        return true;
    }
}
