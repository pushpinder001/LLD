package com.project.app.command.strategy;

import com.project.app.service.ParkingLotService;

public abstract class ICommandStrategy {

    public abstract boolean doYouHandleIt(String cmd);
    public abstract void execute(String[] params);
}
