package com.project.app.command.strategy;

public class GetSlotNosForCarColorCommandStrategy extends ICommandStrategy{
    @Override
    public boolean doYouHandleIt(String cmd) {
        return false;
    }

    @Override
    public void execute(String[] params) {

    }
}
