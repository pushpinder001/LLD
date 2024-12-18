package com.project.app.dao.impl;

import com.project.app.dao.ITransactionDAO;
import com.project.app.entity.Transaction;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTransactionDAO implements ITransactionDAO {
    Map<Integer, Transaction> transactionMap = new HashMap<>();

    @Override
    public int save(Transaction transaction) {
        transaction.setId(transactionMap.size());
        transactionMap.put(transaction.getId(), transaction);
        return transaction.getId();
    }

    @Override
    public List<Transaction> getAllTxnByGroupId(int groupId) {
        return transactionMap.values().stream()
                .filter(txn -> txn.getGroupId() == groupId)
                .map(txn -> txn.toBuilder().txnMetaData(Collections.unmodifiableMap(txn.getTxnMetaData())).build())
                .toList();
    }
}
