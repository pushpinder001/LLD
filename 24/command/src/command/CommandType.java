package command;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum CommandType {
    START,
    EXIT;
    private static Set<String> commands;

    static {
        commands = Arrays.stream(CommandType.values()).map(Enum::name).collect(Collectors.toSet());
    }

    public static boolean isCommandValid(String commandName) {
        return commands.contains(commandName);
    }
}
