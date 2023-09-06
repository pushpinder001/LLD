package com.pushpinder.command;

import com.pushpinder.exception.NoCarPresentForSlot;
import com.pushpinder.model.Command;
import com.pushpinder.service.ParkingLotService;

public class LeaveCommandExecutor extends CommandExecutor{
    public LeaveCommandExecutor(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @Override
    protected boolean validate(Command command) {
        return true;
    }

    @Override
    public void process(Command command) {
        try {
            parkingLotService.unPark(Integer.parseInt(command.getCommandParams().get(0)));
            System.out.printf("Slot number %s is free\n", command.getCommandParams().get(0));
        } catch (NoCarPresentForSlot e) {
            System.out.println(e);
        }
    }
}
