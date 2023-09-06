package com.pushpinder.mode;

import com.pushpinder.command.CommandExecutor;
import com.pushpinder.command.CommandExecutorFactory;
import com.pushpinder.exception.NoCommandFoundException;
import com.pushpinder.service.ParkingLotService;

public abstract class Mode {
    protected ParkingLotService parkingLotService;

    protected CommandExecutorFactory commandExecutorFactory;

    public Mode() {
    }

    protected void executeCommand(String cmd) throws NoCommandFoundException {
        String[] splits = cmd.split(" ");

        if(splits.length ==0) {
            return;
        }

        CommandExecutor commandExecutor = commandExecutorFactory.getCommandExecutor(splits[0]);
        commandExecutor.processCmd(splits);
    }

    public abstract void process();
}
