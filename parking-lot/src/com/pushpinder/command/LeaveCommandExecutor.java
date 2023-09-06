package com.pushpinder.command;

import com.pushpinder.exception.NoCarPresentForSlot;
import com.pushpinder.service.ParkingLotService;

public class LeaveCommandExecutor extends CommandExecutor{
    public LeaveCommandExecutor(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @Override
    protected boolean validate(String[] commandSplits) {
        return true;
    }

    @Override
    public void process(String[] commandSplits) {
        try {
            parkingLotService.unPark(Integer.parseInt(commandSplits[1]));
            System.out.printf("Slot number %s is free\n", commandSplits[1]);
        } catch (NoCarPresentForSlot e) {
            System.out.println(e);
        }
    }
}
