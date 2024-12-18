package com.project.splitwise.dto;

import java.util.Map;

public class TransactionRequest {
    int payerId;
    int userId;
    int groupId;
    TransactionRequest transactionRequest;
    Map<Integer, Double> transactionMetaData;
}