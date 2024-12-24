package com.project.app.command;

import com.project.app.command.strategy.ICommandStrategy;

import java.util.Arrays;
import java.util.List;

public class CommandRunner {
    List<ICommandStrategy> commandStrategies;

    public CommandRunner(List<ICommandStrategy> commandStrategies) {
        this.commandStrategies = commandStrategies;
    }

    public void execute(String cmd) {
        String[] splits = cmd.split(" ");
        String commandString = splits[0];

        ICommandStrategy cmdStr = null;
        for(var commandStrategy : commandStrategies) {
            if(commandStrategy.doYouHandleIt(commandString)) {
                cmdStr = commandStrategy;
            }
        }

        if(cmdStr == null) {
            System.out.println("Invalid Command");
            return;
        }

        //TODO::Add validation
        String[] params = Arrays.asList(splits).subList(1, splits.length).toArray(String[]::new);
        cmdStr.execute(params);
    }
}
