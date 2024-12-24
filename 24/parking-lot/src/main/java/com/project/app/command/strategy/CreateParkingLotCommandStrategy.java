package com.project.app.command.strategy;

import com.project.app.service.factory.ParkingLotFactory;

public class CreateParkingLotCommandStrategy extends ICommandStrategy{

    @Override
    public boolean doYouHandleIt(String cmd) {
        return "create_parking_lot".equals(cmd);
    }

    @Override
    public void execute(String[] params) {
        int noOfSlots = Integer.parseInt(params[0]);
        ParkingLotFactory.createParkingLot(noOfSlots);
        System.out.printf("Created a parking lot with %d slots\n", noOfSlots);
    }
}
