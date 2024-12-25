package com.project.app.service;

import com.project.app.model.Expense;
import com.project.app.strategy.IExpenseSplitStrategy;

import java.util.*;

public class ExpenseService {
    List<Expense> expenses;
    List<IExpenseSplitStrategy> expenseSplitStrategies;

    public ExpenseService(List<IExpenseSplitStrategy> expenseSplitStrategies) {
        expenses = new ArrayList<>();
        this.expenseSplitStrategies = expenseSplitStrategies;
    }

    public void createExpense(Expense expense) {
        expenses.add(expense);
    }

    private Map<String[], Double> filterSplitByUserId(Map<String[], Double> userSplitInfo, String userId) {
        Map<String[], Double> result = new HashMap<>();
        for(var entry : userSplitInfo.entrySet()) {
            if(entry.getKey()[0].equals(userId) ||entry.getKey()[1].equals(userId) ) {
                result.put(entry.getKey(), entry.getValue());
            }
        }

        return result;
    }

    public Map<String[], Double> show(String userId) {
        Map<String, Double> userIdToSplit = new HashMap<>();
        for(var expense : expenses) {
            for(var expenseSplitStrategy : expenseSplitStrategies) {
                if(expenseSplitStrategy.doYouHandleIt(expense)) {
                    Map<String, Double> expenseSplit = expenseSplitStrategy.splitExpense(expense);
                    for(var entry : expenseSplit.entrySet()) {
                        userIdToSplit.merge(entry.getKey(), entry.getValue(), Double::sum);
                    }
                }
            }
        }

        Map<String[], Double> userSplitInfo = simplyfy(userIdToSplit);
        if(userId != null) {
            return filterSplitByUserId(userSplitInfo, userId);
        }

        return userSplitInfo;
    }

    private Map<String[], Double> simplyfy(Map<String, Double> userIdToSplit) {
        Map<String[], Double> result = new HashMap<>();
        Deque<Map.Entry<String, Double>> positiveBalance = new ArrayDeque<>();
        Deque<Map.Entry<String, Double>> negativeBalance = new ArrayDeque<>();

        for(var entry : userIdToSplit.entrySet()) {
            if(entry.getValue()>0) {
                positiveBalance.offerLast(new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue()));
            } else {
                negativeBalance.offerLast(new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue()));
            }
        }

        while(positiveBalance.size()>0) {
            double negVal = negativeBalance.peekLast().getValue();
            double posVal = positiveBalance.peekLast().getValue();
            if(posVal >= Math.abs(negVal)) {
                result.put(new String[]{negativeBalance.peekLast().getKey(),
                                positiveBalance.peekLast().getKey()},
                        Math.abs(negVal));

                positiveBalance.peekLast().setValue(posVal+negVal);
                if(positiveBalance.peekLast().getValue()==0) {
                    positiveBalance.pollLast();
                }
                negativeBalance.pollLast();
            } else {
                result.put(new String[]{negativeBalance.peekLast().getKey(),
                                positiveBalance.peekLast().getKey()},
                        positiveBalance.pollLast().getValue());
                positiveBalance.pollLast();
                negativeBalance.peekLast().setValue(posVal+negVal);
                if(negativeBalance.peekLast().getValue()==0) {
                    negativeBalance.pollLast();
                }
            }
        }

        return result;
    }
}
