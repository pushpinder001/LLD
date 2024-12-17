package command;

import java.util.Arrays;
import java.util.Map;

public class CommandRunner {
    private final Map<String, ICommandHandler> map;

    public CommandRunner(Map<String, ICommandHandler> map) {
         this.map = map;
    }

    public void process(String command) {
        if(!CommandType.isCommandValid(command)) {
            System.out.println("Invalid Command");
            return;
        }
        String[] splits = command.split(" ");
        String commandName = splits[0];
        String[] commandParam = Arrays.asList(splits).subList(1, splits.length).toArray(String[]::new);
        map.get(command).handle(commandParam);
    }
}
