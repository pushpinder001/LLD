package com.project.app.command.strategy;

import com.project.app.service.ParkingLotService;

public class StatusCommandHandler implements ICommandHandler{
    @Override
    public boolean validateParams(String[] params) {
        return true;
    }

    @Override
    public boolean doYouHandleIt(String cmd) {
        return "status".equals(cmd);
    }

    @Override
    public void execute(String[] params) {
        ParkingLotService.getInstance().getStatus();
    }
}
