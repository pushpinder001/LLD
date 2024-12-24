package com.project.app.command;

import com.project.app.command.strategy.ICommandHandler;

import java.util.Arrays;
import java.util.List;

public class CommandRunner {
    List<ICommandHandler> commandStrategies;

    public CommandRunner(List<ICommandHandler> commandStrategies) {
        this.commandStrategies = commandStrategies;
    }

    public void execute(String cmd) {
        String[] splits = cmd.split(" ");
        String commandString = splits[0];

        ICommandHandler cmdStr = null;
        for(var commandStrategy : commandStrategies) {
            if(commandStrategy.doYouHandleIt(commandString)) {
                cmdStr = commandStrategy;
            }
        }

        if(cmdStr == null) {
            System.out.println("Invalid Command");
            return;
        }

        String[] params = Arrays.asList(splits).subList(1, splits.length).toArray(String[]::new);
        if(!cmdStr.validateParams(params)) {
            System.out.println("Invalid command params");
            return;
        }
        cmdStr.execute(params);
    }
}
