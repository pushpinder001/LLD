package com.project.app.service.strategy;

public interface ISlotFinderStrategy {
    boolean addSlot(int slotNo);

    boolean removeSlot(int slotNo);

    Integer getSlot();
}
