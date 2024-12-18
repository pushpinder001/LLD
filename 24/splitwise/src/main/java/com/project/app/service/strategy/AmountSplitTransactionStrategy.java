package com.project.app.service.strategy;

import com.project.app.entity.Transaction;

import java.util.HashMap;
import java.util.Map;

public class AmountSplitTransactionStrategy implements ITransactionSplitStrategy {

    public AmountSplitTransactionStrategy() {
    }

    @Override
    public Map<Integer, Double> getSplit(Transaction transaction) {
        Map<Integer, Double> splitMap = new HashMap<>();
        splitMap.put(transaction.getPayerId(), transaction.getAmount());


        for(var entry : transaction.getTxnMetaData().entrySet()) {
            splitMap.merge(entry.getKey(), -entry.getValue(), Double::sum);
        }
        return splitMap;
    }
}
