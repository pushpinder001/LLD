package com.project.app.command.strategy;

import com.project.app.entity.ParkingLot;
import com.project.app.service.ParkingLotService;
import com.project.app.service.strategy.ISlotFinderStrategy;
import com.project.app.service.strategy.NearestSlotFinderStrategy;

public class CreateParkingLotCommandHandler extends ICommandHandler{

    public CreateParkingLotCommandHandler(ParkingLotService parkingLotService) {
        super(parkingLotService);
    }

    @Override
    public boolean validateParams(String[] params) {
        return params.length>0;
    }

    @Override
    public boolean doYouHandleIt(String cmd) {
        return "create_parking_lot".equals(cmd);
    }

    @Override
    public void execute(String[] params) {
        int noOfSlots = Integer.parseInt(params[0]);
        ParkingLot parkingLot = new ParkingLot(noOfSlots);
        ISlotFinderStrategy slotFinderStrategy = new NearestSlotFinderStrategy(parkingLot.getSlotNos());
        try {
            parkingLotService.createParkingLot(parkingLot, slotFinderStrategy);
        } catch (Exception e) {
            System.out.println("Parking already exists");
            return;
        }
        System.out.printf("Created a parking lot with %d slots\n", noOfSlots);
    }
}
