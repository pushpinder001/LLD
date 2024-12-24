package com.project.app.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Slot {
    private final Integer slotNo;

    @Setter
    private Ticket ticket;

    public Slot(Integer slotNo) {
        this.slotNo = slotNo;
    }

    public boolean freeSlot() {
        ticket = null;
        return true;
    }

    public boolean isSlotFree() {
        return ticket==null;
    }

}
