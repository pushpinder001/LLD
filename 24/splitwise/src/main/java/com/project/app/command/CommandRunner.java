package com.project.app.command;

import com.project.app.command.type.CommandType;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class CommandRunner {
    Map<CommandType, ICommandHandler> commandTypeToCommandHandler;
    private static String delimiter = " ";

    public CommandRunner(Map<CommandType, ICommandHandler> commandTypeToCommandHandler) throws Exception {
        //Validation
        if(commandTypeToCommandHandler == null) {
            throw new Exception("The value of commandTypeToCommandHandler is nul");
        }
        this.commandTypeToCommandHandler = Collections.unmodifiableMap(commandTypeToCommandHandler);
    }

    public void execute(String cmd) {
        String[] splits = cmd.split(delimiter);

        String command = splits[0];
        CommandType commandType = CommandType.valueOf(command);

        //Validation
        if(commandType == null) {
            System.out.println("Invalid command " + cmd);
            return;
        }

        String[] params = Arrays.asList(splits).subList(1, splits.length).toArray(String[]::new);

        commandTypeToCommandHandler.get(commandType).execute(params);
    }
}
