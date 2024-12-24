package com.project.app.command.strategy;

import com.project.app.service.ParkingLotService;

public class ParkVehicleCommandStrategy extends ICommandStrategy{
    @Override
    public boolean doYouHandleIt(String cmd) {
        return "park".equals(cmd);
    }

    @Override
    public void execute(String[] params) {
        try {
            ParkingLotService.getInstance().parkVehicle(params[0], params[1]);
        } catch (Exception e) {
            System.out.println("Sorry, parking lot is full");
        }
    }
}
