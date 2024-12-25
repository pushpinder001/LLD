package com.project.app.command;

import com.project.app.service.ExpenseService;
import lombok.NonNull;

import java.util.Map;

public class ShowCommandHandler implements ICommandHandler {
    private final ExpenseService expenseService;

    public ShowCommandHandler(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @Override
    public boolean doYouHandlerIt(String commandName) {
        return commandName.equals("SHOW");
    }

    @Override
    public boolean validateParams(String[] params) {
        return false;
    }

    @Override
    public void execute(String[] params) {
        String userId = params.length>0?params[0]:null;
        Map<String[], Double> userSplitInfo = expenseService.show(userId);
        if(userSplitInfo.isEmpty()){
            System.out.println("No balances");
        } else {
            for(var entry : userSplitInfo.entrySet()) {
                System.out.printf("%s owes %s %.2f\n", entry.getKey()[0],
                        entry.getKey()[1], entry.getValue());
            }
        }
    }
}
