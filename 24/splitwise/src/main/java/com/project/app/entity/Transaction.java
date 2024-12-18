package com.project.app.entity;

import com.project.app.service.strategy.ITransactionSplitStrategy;
import com.project.app.type.TransactionType;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Transaction {
    private int id;
    private int userId;
    private int payerId;
    private double amount;
    private int groupId;
    private TransactionType transactionType;
    private Map<Integer, Double> txnMetaData;

    public String toString(ITransactionSplitStrategy transactionSplitStrategy) {
        return "Transaction{" +
                "id=" + id +
                ", userId=" + userId +
                ", payerId=" + payerId +
                ", amount=" + amount +
                ", groupId=" + groupId +
                ", split=" + transactionSplitStrategy.getSplit(this).toString()+
                '}';
    }
}
