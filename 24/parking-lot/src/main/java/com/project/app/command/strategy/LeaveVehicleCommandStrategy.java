package com.project.app.command.strategy;

import com.project.app.service.ParkingLotService;

public class LeaveVehicleCommandStrategy extends ICommandStrategy{
    @Override
    public boolean doYouHandleIt(String cmd) {
        return "leave".equals(cmd);
    }

    @Override
    public void execute(String[] params) {
        int slotNo = Integer.parseInt(params[0]);
        try {
            ParkingLotService.getInstance().unParkVehicle(slotNo);
        } catch (Exception e) {
            System.out.println("Not found");
        }
    }
}
