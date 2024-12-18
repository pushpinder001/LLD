package com.project.splitwise.command.type;

import java.util.HashMap;
import java.util.Map;

public enum CommandType {
    CREATE_USER;

    private static Map<String, CommandType> commandToCommandType;

    static {
        commandToCommandType = new HashMap<>();
        for(CommandType commandType: CommandType.values()) {
            commandToCommandType.put(commandType.toString(), commandType);
        }
    }

    public static CommandType getCommandType(String command) {
        return commandToCommandType.get(command);
    }
}
