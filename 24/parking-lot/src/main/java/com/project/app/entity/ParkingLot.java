package com.project.app.entity;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private Map<Integer, Slot> slotIdToSlot;

    public ParkingLot(int noOfSlots) {
        if(noOfSlots<0) {
            throw new RuntimeException("Invalid noOfSlots");
        }

        this.slotIdToSlot = new HashMap<>();
        for(int i=1; i<=noOfSlots; i++) {
            slotIdToSlot.put(i, new Slot(i));
        }
    }

    public boolean addTicket(@NonNull final Integer slotNo, @NonNull final Ticket ticket) {
        Slot slot = slotIdToSlot.get(slotNo);
        slot.setTicket(ticket);
        return true;
    }

    public boolean isSlotValid(@NonNull final Integer slotNo) {
        return slotNo>=1 && slotNo<=slotIdToSlot.size();
    }

    public boolean isSlotFree(@NonNull final Integer slotNo) {
        return slotIdToSlot.get(slotNo).isSlotFree();
    }

    public boolean freeSlot(@NonNull final Integer slotNo) {
        return slotIdToSlot.get(slotNo).freeSlot();
    }

    public List<Integer> getSlotNos() {
        return new ArrayList<>(slotIdToSlot.keySet());
    }

    public Ticket getTicket(@NonNull final Integer slotNo) {
        return slotIdToSlot.get(slotNo).getTicket();
    }
}
