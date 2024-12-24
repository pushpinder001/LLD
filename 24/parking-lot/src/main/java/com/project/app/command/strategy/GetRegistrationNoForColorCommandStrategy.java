package com.project.app.command.strategy;

public class GetRegistrationNoForColorCommandStrategy extends ICommandStrategy{
    @Override
    public boolean doYouHandleIt(String cmd) {
        return false;
    }

    @Override
    public void execute(String[] params) {

    }
}
