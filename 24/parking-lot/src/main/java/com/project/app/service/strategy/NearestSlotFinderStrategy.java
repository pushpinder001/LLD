package com.project.app.service.strategy;

import java.util.TreeSet;

public class NearestSlotFinderStrategy implements ISlotFinderStrategy{
    TreeSet<Integer> slotNos = new TreeSet<>();

    @Override
    public boolean addSlot(int slotNo) {
        slotNos.add(slotNo);
        return false;
    }

    @Override
    public boolean removeSlot(int slotNo) {
        slotNos.remove(slotNo);
        return true;
    }

    @Override
    public Integer getSlot() {
        return slotNos.pollFirst();
    }
}
