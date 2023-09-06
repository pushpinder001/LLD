package com.pushpinder.command;

import com.pushpinder.model.Command;
import com.pushpinder.service.ParkingLotService;

public abstract class CommandExecutor {
    protected ParkingLotService parkingLotService;

    protected abstract boolean validate(Command command);

    public void processCmd(Command command) {
        this.validate(command);
        this.process(command);
    }
    protected abstract void process(Command command);

}
