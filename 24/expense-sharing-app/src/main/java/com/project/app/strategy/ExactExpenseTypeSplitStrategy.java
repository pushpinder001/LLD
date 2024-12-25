package com.project.app.strategy;

import com.project.app.model.Expense;
import com.project.app.type.ExpenseType;

import java.util.HashMap;
import java.util.Map;

public class ExactExpenseTypeSplitStrategy implements IExpenseSplitStrategy {
    @Override
    public boolean validateExpense(Expense expense) {
        double totalAmount = expense.getTotalAmount();

        double splitTotalAmt = expense.getMetaData().values().stream().mapToDouble(i->i).sum();
        return totalAmount == splitTotalAmt;
    }

    @Override
    public boolean doYouHandleIt(Expense expense) {
        return expense.getExpenseType().equals(ExpenseType.EXACT);
    }

    @Override
    public Map<String, Double> splitExpense(Expense expense) {
        double totalAmount = expense.getTotalAmount();
        String paidBy = expense.getPayedBy();
        var metaData = expense.getMetaData();
        Map<String, Double> result = new HashMap<>();

        result.put(paidBy, totalAmount);
        for(var entry : metaData.entrySet()) {
            result.merge(entry.getKey(), -entry.getValue(), Double::sum);
        }
        return result;
    }
}
