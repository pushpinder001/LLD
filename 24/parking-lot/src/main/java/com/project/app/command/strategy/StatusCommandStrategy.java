package com.project.app.command.strategy;

import com.project.app.service.ParkingLotService;

public class StatusCommandStrategy extends ICommandStrategy{
    @Override
    public boolean doYouHandleIt(String cmd) {
        return "status".equals(cmd);
    }

    @Override
    public void execute(String[] params) {
        ParkingLotService.getInstance().getStatus();
    }
}
