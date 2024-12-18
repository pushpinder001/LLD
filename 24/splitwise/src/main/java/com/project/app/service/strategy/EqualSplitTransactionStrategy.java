package com.project.app.service.strategy;

import com.project.app.entity.Group;
import com.project.app.entity.Transaction;
import com.project.app.service.GroupService;

import java.util.HashMap;
import java.util.Map;

public class EqualSplitTransactionStrategy implements ITransactionSplitStrategy {
    private final GroupService groupService;

    public EqualSplitTransactionStrategy(GroupService groupService) {
        this.groupService = groupService;
    }

    @Override
    public Map<Integer, Double> getSplit(Transaction transaction) {
        Group group = groupService.getGroupById(transaction.getGroupId());

        Map<Integer, Double> splitMap = new HashMap<>();
        splitMap.put(transaction.getPayerId(), transaction.getAmount());

        double splitAmount = transaction.getAmount()/group.getUserIds().size();

        for(var userId : group.getUserIds()) {
            splitMap.merge(userId, -splitAmount, Double::sum);
        }
        return splitMap;
    }
}
