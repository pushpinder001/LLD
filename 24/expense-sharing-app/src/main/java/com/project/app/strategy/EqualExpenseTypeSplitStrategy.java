package com.project.app.strategy;

import com.project.app.model.Expense;
import com.project.app.type.ExpenseType;

import java.util.HashMap;
import java.util.Map;

public class EqualExpenseTypeSplitStrategy implements IExpenseSplitStrategy {
    @Override
    public boolean validateExpense(Expense expense) {
        return true;
    }

    @Override
    public boolean doYouHandleIt(Expense expense) {
        return expense.getExpenseType().equals(ExpenseType.EQUAL);
    }

    @Override
    public Map<String, Double> splitExpense(Expense expense) {
        double amount = expense.getTotalAmount();
        String paidBy = expense.getPayedBy();
        var metaData = expense.getMetaData();

        double share = amount/metaData.size();
        Map<String, Double> result = new HashMap<>();

        result.put(paidBy, amount);
        for(var it : metaData.keySet()) {
            result.merge(it, -share, Double::sum);
        }

        return result;
    }
}
