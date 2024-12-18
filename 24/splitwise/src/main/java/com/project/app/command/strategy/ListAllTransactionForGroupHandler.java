package com.project.app.command.strategy;

import com.project.app.command.ICommandHandler;
import com.project.app.service.TransactionService;

public class ListAllTransactionForGroupHandler implements ICommandHandler {
    TransactionService transactionService;

    public ListAllTransactionForGroupHandler(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public void execute(String[] params) {
        //TODO:: Add validation
        int groupId = Integer.parseInt(params[0]);
        transactionService.listAllTransactionForGroup(groupId);
    }
}
