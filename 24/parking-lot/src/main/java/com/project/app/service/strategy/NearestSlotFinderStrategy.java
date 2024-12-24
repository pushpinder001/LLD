package com.project.app.service.strategy;

import com.project.app.entity.Slot;

import java.util.List;

public class NearestSlotFinderStrategy implements ISlotFinderStrategy{
    @Override
    public Slot getSlot(List<Slot> slots) {
        for(var slot : slots) {
            if(slot.isSlotFree()) {
                return slot;
            }
        }

        return null;
    }
}
