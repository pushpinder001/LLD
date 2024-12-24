package com.project.app.command.strategy;


public interface ICommandStrategy {
    boolean validateParams(String[] params);
    boolean doYouHandleIt(String cmd);
    void execute(String[] params);
}
