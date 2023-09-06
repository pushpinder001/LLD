package com.pushpinder.mode;

import com.pushpinder.command.CommandExecutor;
import com.pushpinder.command.CommandExecutorFactory;
import com.pushpinder.exception.NoCommandFoundException;
import com.pushpinder.model.Command;
import com.pushpinder.service.ParkingLotService;

public abstract class Mode {
    protected ParkingLotService parkingLotService;

    protected CommandExecutorFactory commandExecutorFactory;

    public Mode() {
    }

    protected void executeCommand(String cmd) throws NoCommandFoundException {
        Command command = new Command(cmd);
        CommandExecutor commandExecutor = commandExecutorFactory.getCommandExecutor(command);
        commandExecutor.processCmd(command);
    }

    public abstract void process();
}
