package com.project.app.service;

import com.project.app.dao.ITransactionDAO;
import com.project.app.entity.Transaction;
import com.project.app.service.strategy.ITransactionSplitStrategy;
import com.project.app.type.TransactionType;

import java.util.Map;

public class TransactionService {
    private final ITransactionDAO transactionDAO;
    private final Map<TransactionType, ITransactionSplitStrategy> tnxTypeToTxnSplitStrategy;

    public TransactionService(ITransactionDAO transactionDAO,
                                Map<TransactionType, ITransactionSplitStrategy> tnxTypeToTxnSplitStrategy) {
        this.transactionDAO = transactionDAO;
        this.tnxTypeToTxnSplitStrategy = tnxTypeToTxnSplitStrategy;
    }

    public int addTransaction(int payerId, double amount, int userId, TransactionType transactionType,
                                Map<Integer, Double> txnMetaData) {
        //TODO::ADD Validation
        Transaction transaction = Transaction.builder()
                    .payerId(payerId)
                    .amount(amount)
                    .userId(userId)
                .transactionType(transactionType)
                .txnMetaData(txnMetaData)
                .build();

        return transactionDAO.save(transaction);
    }

    public void listAllTransactionForGroup(Integer groupId) {
        System.out.printf(".....Transactions for Group %d.....\n", groupId);
        for(Transaction txn : transactionDAO.getAllTxnByGroupId(groupId)) {
            System.out.println(txn.toString(tnxTypeToTxnSplitStrategy.get(txn.getTransactionType())));
        }
    }

}

/**
 * CREATE_USER
 * CREATE_USER
 * CREATE_GROUP 0 1
 * LIST_ALL_GROUPS_FOR_USER 0
 * CREATE_TRANSACTION_TO_GROUP 0 100 0 EQUAL_SPLIT
 * LIST_ALL_TXNS_IN_GROUP 0
 */
