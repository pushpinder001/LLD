package com.project.app.command;

import com.project.app.model.Expense;
import com.project.app.service.ExpenseService;
import com.project.app.type.ExpenseType;

import java.util.HashMap;
import java.util.Map;

public class ExpenseCommandHandler implements ICommandHandler {
    private final ExpenseService expenseService;

    public ExpenseCommandHandler(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @Override
    public boolean doYouHandlerIt(String commandName) {
        return commandName.equals("EXPENSE");
    }

    @Override
    public boolean validateParams(String[] params) {
        return false;
    }

    @Override
    public void execute(String[] params) {
        String payerId = params[0];
        Double payedAmount = Double.parseDouble(params[1]);

        Map<String, Double> metaData = new HashMap<>();

        int noOfUsers = Integer.parseInt(params[2]);


        if(params[3+noOfUsers].equals("EQUAL")) {
            for (int i = 1; i <= noOfUsers; i++) {
                metaData.put(params[2+i], 0.0);
            }
            expenseService.createExpense(new Expense(payedAmount, payerId, ExpenseType.EQUAL, metaData));
        } else if(params[3+noOfUsers].equals("EXACT")){
            for (int i = 1; i <= noOfUsers; i++) {
                metaData.put(params[2+i], Double.parseDouble(params[3+noOfUsers+i]));
            }
            expenseService.createExpense(new Expense(payedAmount, payerId, ExpenseType.EXACT, metaData));
        }

    }
}
