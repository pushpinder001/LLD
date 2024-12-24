package com.project.app.command.strategy;


public interface ICommandHandler {
    boolean validateParams(String[] params);
    boolean doYouHandleIt(String cmd);
    void execute(String[] params);
}
