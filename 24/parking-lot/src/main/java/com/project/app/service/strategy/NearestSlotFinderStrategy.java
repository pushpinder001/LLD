package com.project.app.service.strategy;

import lombok.NonNull;

import java.util.List;
import java.util.TreeSet;

public class NearestSlotFinderStrategy implements ISlotFinderStrategy{
    TreeSet<Integer> slotNos = new TreeSet<>();

    public NearestSlotFinderStrategy(@NonNull final List<Integer> slotNos) {
        for(var it : slotNos) {
            addSlot(it);
        }
    }

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
