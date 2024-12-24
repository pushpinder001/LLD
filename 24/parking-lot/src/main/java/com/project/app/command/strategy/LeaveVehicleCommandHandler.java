package com.project.app.command.strategy;

import com.project.app.service.ParkingLotService;

public class LeaveVehicleCommandHandler extends ICommandHandler{
    public LeaveVehicleCommandHandler(ParkingLotService parkingLotService) {
        super(parkingLotService);
    }

    @Override
    public boolean validateParams(String[] params) {
        return params.length>0;
    }

    @Override
    public boolean doYouHandleIt(String cmd) {
        return "leave".equals(cmd);
    }

    @Override
    public void execute(String[] params) {
        int slotNo = Integer.parseInt(params[0]);
        try {
            parkingLotService.unParkVehicle(slotNo);
            System.out.printf("Slot number %d is free\n", slotNo);
        } catch (Exception e) {
            System.out.println("Not found");
        }
    }
}
