package com.project.app.command;

public interface ICommandHandler {
    boolean doYouHandlerIt(String commandName);
    boolean validateParams(String[] params);
    void execute(String[] params);
}
