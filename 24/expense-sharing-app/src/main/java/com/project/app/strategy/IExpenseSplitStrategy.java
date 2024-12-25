package com.project.app.strategy;

import com.project.app.model.Expense;

import java.util.Map;

public interface IExpenseSplitStrategy {
    boolean validateExpense(Expense expense);

    boolean doYouHandleIt(Expense expense);

    Map<String, Double> splitExpense(Expense expense);
}
