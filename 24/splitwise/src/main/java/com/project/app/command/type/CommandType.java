package com.project.app.command.type;

import java.util.HashMap;
import java.util.Map;

public enum CommandType {
    CREATE_USER,
    LIST_ALL_USERS,
    CREATE_GROUP,
    LIST_ALL_GROUPS_FOR_USER,
    LIST_ALL_TXNS_IN_GROUP,
    CREATE_TRANSACTION_TO_GROUP;


    private static final Map<String, CommandType> commandToCommandType;

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
