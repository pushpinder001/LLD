package com.project.app.command.strategy;

import com.project.app.command.ICommandHandler;
import com.project.app.service.TransactionService;
import com.project.app.type.TransactionType;

import java.util.HashMap;
import java.util.Map;

public class AddTransactionCommandHandler implements ICommandHandler {
    TransactionService transactionService;

    public AddTransactionCommandHandler(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public void execute(String[] params) {
        if(params == null || params.length <4) {
            System.out.println("Invalid params for command");
            return;
        }

        int payerId = Integer.parseInt(params[0]);
        double amount = Double.parseDouble(params[1]);
        int userId = Integer.parseInt(params[2]);
        TransactionType transactionType = TransactionType.valueOf(params[3]);

        Map<Integer, Double> map = new HashMap<>();
        for(int i=4; i<params.length; i+=2) {
            int uId = Integer.parseInt(params[0]);
            double splitMetaData = Double.parseDouble(params[1]);
            map.put(uId, splitMetaData);
        }

        int txnId = transactionService.addTransaction(payerId, amount, userId, transactionType, map);

        System.out.println("Transaction successfully create with id " + txnId);
    }
}
