package com.project.app.service;

import com.project.app.dao.IGroupDAO;
import com.project.app.entity.Group;

import java.util.Arrays;
import java.util.Map;

public class GroupService {
    private final IGroupDAO groupDAO;

    public GroupService(IGroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    public int createGroup(Integer[] userIds) {
        //TODO::validation
        return groupDAO.save(new Group().builder().userIds(Arrays.asList(userIds)).build());
    }

    public Group getGroupById(int groupId) {
        return groupDAO.getById(groupId);
    }

    public void listAllGroups(Integer userId) {
        System.out.printf(".....Groups for User %d.....\n", userId);
        System.out.println(groupDAO.getAllGroups(userId));
    }

    /**
     * This method will get all txn of user
     * @param userId
     * @param groupId
     * @return
     */
    public double getNetBalanceForUserAndGroup(Integer userId, int groupId) {
        //TODO:: Get all tnxs for userId and groupId and sum split val for userId across all tnx
        return 0.0;
    }

    public double getNetBalanceForUser(Integer userId) {
        //TODO:: Get all the groupIds of user and call getNetBalanceForUserAndGroup and sum for all groupId
        return 0.0;
    }

    public Map<Integer, Double> simplify(Integer groupId) {
        /***
         TODO:: Get the net balances of all the user across all groups and then use greedy approach to simplify
         **/
        return null;
    }
}
