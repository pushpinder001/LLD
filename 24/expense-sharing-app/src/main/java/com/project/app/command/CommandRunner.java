package com.project.app.command;

import lombok.NonNull;

import java.util.Arrays;
import java.util.List;

public class CommandRunner {
    private final String separator = " ";
    List<ICommandHandler> commandHandlers;

    public CommandRunner(List<ICommandHandler> commandHandlers) {
        this.commandHandlers = commandHandlers;
    }

    public void run(@NonNull final String command) {
        //TODO::Add validation
        String[] splits = command.split(separator);

        for(var commandHandler : commandHandlers) {
            if(commandHandler.doYouHandlerIt(splits[0])) {
                String[] params = Arrays.asList(splits).subList(1, splits.length).toArray(String[]::new);
                commandHandler.execute(params);
                return;
            }
        }

        throw new RuntimeException("Invalid Command");
    }
}
