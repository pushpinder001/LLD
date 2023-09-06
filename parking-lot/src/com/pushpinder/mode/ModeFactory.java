package com.pushpinder.mode;

import com.pushpinder.command.CommandExecutorFactory;
import com.pushpinder.exception.InvalidModeException;
import com.pushpinder.service.ParkingLotService;

public final class ModeFactory {
    protected ParkingLotService parkingLotService;
    protected CommandExecutorFactory commandExecutorFactory;

    public enum ModeType {
        CONSOLE;
    }
    public static Mode getMode(String[] args, ParkingLotService parkingLotService, CommandExecutorFactory commandExecutorFactory) throws InvalidModeException {

        if(args.length==0 ) {
            throw new InvalidModeException();
        }

        if(args[0].equals(ModeType.CONSOLE.name())) {
            return new ConsoleInputMode(parkingLotService, commandExecutorFactory);
        } else {
            throw new InvalidModeException();
        }
    }
}
