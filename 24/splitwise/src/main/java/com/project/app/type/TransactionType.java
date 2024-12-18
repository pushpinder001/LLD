package com.project.app.type;

import java.util.HashMap;
import java.util.Map;

public enum TransactionType {
    EQUAL_SPLIT,
    UNEQUAL_SPLIT,
    PERCENT_SPLIT;


    private static final Map<String, TransactionType> transactionoTransactionType;

    static {
        transactionoTransactionType = new HashMap<>();
        for(TransactionType transactionType: TransactionType.values()) {
            transactionoTransactionType.put(transactionType.toString(), transactionType);
        }
    }

    public static TransactionType getCommandType(String transaction) {
        return transactionoTransactionType.get(transaction);
    }
}
