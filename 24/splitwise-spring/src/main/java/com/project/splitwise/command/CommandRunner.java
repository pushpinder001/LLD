package com.project.splitwise.command;

import com.project.splitwise.command.strategy.ICommandHandler;
import com.project.splitwise.command.type.CommandType;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;

@Component
public class CommandRunner {
    @Resource(name="commandHandler")
    Map<CommandType, ICommandHandler> commandTypeToCommandHandler;
    private static String delimiter = " ";

    public CommandRunner() {
    }

    public void execute(String cmd) {
        String[] splits = cmd.split(delimiter);

        //TODO::Validate command
        String command = splits[0];
        CommandType commandType = CommandType.getCommandType(command);

        String[] params = Arrays.asList(splits).subList(1, splits.length).toArray(String[]::new);

        commandTypeToCommandHandler.get(commandType).execute(params);
    }
}
