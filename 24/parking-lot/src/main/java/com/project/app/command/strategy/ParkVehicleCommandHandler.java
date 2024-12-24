package com.project.app.command.strategy;

import com.project.app.entity.Ticket;
import com.project.app.service.ParkingLotService;

public class ParkVehicleCommandHandler extends ICommandHandler{
    public ParkVehicleCommandHandler(ParkingLotService parkingLotService) {
        super(parkingLotService);
    }

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
            Ticket ticket = parkingLotService.parkVehicle(params[0], params[1]);
            System.out.printf("Allocated slot number: %d\n", ticket.getSlotNo());
        } catch (Exception e) {
            System.out.println("Sorry, parking lot is full");
        }
    }
}
