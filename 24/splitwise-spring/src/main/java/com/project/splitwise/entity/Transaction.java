package com.project.splitwise.entity;

import lombok.Getter;

import java.util.Map;

@Getter
public class Transaction {
    private int id;
    private int userId;
    private int payerId;
    private double amount;
    private int groupId;
    private Map<Integer, Double> txnMetaData;
}
