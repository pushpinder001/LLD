package com.project.app.service.strategy;

import com.project.app.entity.Transaction;

import java.util.HashMap;
import java.util.Map;

public class PercentSplitTransactionStrategy implements ITransactionSplitStrategy {
    public PercentSplitTransactionStrategy() {
    }

    @Override
    public Map<Integer, Double> getSplit(Transaction transaction) {
        Map<Integer, Double> splitMap = new HashMap<>();
        double amount = transaction.getAmount();
        splitMap.put(transaction.getPayerId(), amount);

        for(var entry : transaction.getTxnMetaData().entrySet()) {
            splitMap.merge(entry.getKey(), -((amount*entry.getValue())/100.0), Double::sum);
        }
        return splitMap;
    }
}
