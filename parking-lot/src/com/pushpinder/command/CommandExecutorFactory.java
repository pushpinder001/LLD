package com.pushpinder.command;

import com.pushpinder.exception.NoCommandFoundException;
import com.pushpinder.model.Command;
import com.pushpinder.model.ParkingLot;
import com.pushpinder.service.ParkingLotService;

import java.util.HashMap;
import java.util.Map;

public final class CommandExecutorFactory {
    public enum CommandType{
        create_parking_lot, park, leave;
    }

    private Map<String, CommandExecutor> commandToCommandExecutor = new HashMap<>();

    public CommandExecutorFactory(ParkingLotService parkingLotService) {
        commandToCommandExecutor.put(CommandType.create_parking_lot.name(), new CreateParkingLotCommandExecutor(parkingLotService));
        commandToCommandExecutor.put(CommandType.park.name(), new ParkCommandExecutor(parkingLotService));
        commandToCommandExecutor.put(CommandType.leave.name(), new LeaveCommandExecutor(parkingLotService));
    }

    static {

    }
    public CommandExecutor getCommandExecutor(Command command) throws NoCommandFoundException {
        String cmd = command.getName();
        if(cmd.equals(CommandType.create_parking_lot.name())) {
           return commandToCommandExecutor.get(CommandType.create_parking_lot.name());
        } else if(cmd.equals(CommandType.park.name())) {
            return commandToCommandExecutor.get(CommandType.park.name());
        } else if(cmd.equals(CommandType.leave.name())) {
            return commandToCommandExecutor.get(CommandType.leave.name());
        } else {
            throw new NoCommandFoundException();
        }
    }
}
