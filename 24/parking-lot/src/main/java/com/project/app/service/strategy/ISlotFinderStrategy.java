package com.project.app.service.strategy;

import com.project.app.entity.Slot;

import java.util.List;

public interface ISlotFinderStrategy {
    Slot getSlot(List<Slot> slots);
}
