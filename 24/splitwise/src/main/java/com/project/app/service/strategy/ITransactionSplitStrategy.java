package com.project.app.service.strategy;

import com.project.app.entity.Transaction;

import java.util.Map;

public interface ITransactionSplitStrategy {
    Map<Integer, Double> getSplit(Transaction transaction);
}
