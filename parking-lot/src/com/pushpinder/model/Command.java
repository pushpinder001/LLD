package com.pushpinder.model;

import com.pushpinder.exception.NoCommandFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Command {
    private String commandName;

    private List<String> commandParams;

    public Command(String inputString) throws NoCommandFoundException {
        List<String> tokens = Arrays.stream(inputString.trim().split(" "))
                .map(str->str.trim())
                .collect(Collectors.toList());

        if(tokens.size() == 0) {
            throw new NoCommandFoundException();
        }

        this.commandName = tokens.get(0);
        tokens.remove(0);
        this.commandParams = tokens;
    }

    public String getName() {
        return commandName;
    }

    public List<String> getCommandParams() {
        return commandParams;
    }
}
