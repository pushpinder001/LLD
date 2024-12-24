package com.project.app.command.strategy;


import com.project.app.service.ParkingLotService;

public abstract class ICommandHandler {
    protected final ParkingLotService parkingLotService;

    public ICommandHandler(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    public abstract boolean validateParams(String[] params);
    public abstract boolean doYouHandleIt(String cmd);
    public abstract void execute(String[] params);
}
