package com.project.app.command.strategy;

import com.project.app.entity.Ticket;
import com.project.app.service.ParkingLotService;

public class ParkVehicleCommandStrategy implements ICommandStrategy{
    @Override
    public boolean validateParams(String[] params) {
        return params.length>1;
    }

    @Override
    public boolean doYouHandleIt(String cmd) {
        return "park".equals(cmd);
    }

    @Override
    public void execute(String[] params) {
        try {
            Ticket ticket = ParkingLotService.getInstance().parkVehicle(params[0], params[1]);
            System.out.printf("Allocated slot number: %d\n", ticket.getSlotNo());
        } catch (Exception e) {
            System.out.println("Sorry, parking lot is full");
        }
    }
}
