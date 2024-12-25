package com.project.app.model;

import com.project.app.type.ExpenseType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class Expense {
    private final double totalAmount;
    private final String payedBy;
    private final ExpenseType expenseType;
    private final Map<String, Double> metaData;
}
