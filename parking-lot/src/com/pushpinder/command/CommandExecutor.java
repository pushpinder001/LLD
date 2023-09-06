package com.pushpinder.command;

import com.pushpinder.service.ParkingLotService;

public abstract class CommandExecutor {
    protected ParkingLotService parkingLotService;

    protected abstract boolean validate(String[] commandSplits);

    public void processCmd(String[] commandSplits) {
        this.validate(commandSplits);
        this.process(commandSplits);
    }
    protected abstract void process(String[] commandSplits);

}
