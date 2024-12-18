package com.project.app.dao.impl;

import com.project.app.dao.IGroupDAO;
import com.project.app.entity.Group;

import java.util.*;

public class InMemoryGroupDAO implements IGroupDAO {
    Map<Integer, Group> groupMap = new HashMap<>();

    @Override
    public int save(Group group) {
        group.setId(groupMap.size());
        groupMap.put(group.getId(), group);
        return group.getId();
    }

    @Override
    public boolean containGroup(int id) {
        return groupMap.containsKey(id);
    }

    @Override
    public Group getById(int id) {
        return groupMap.get(id).toBuilder().build();
    }

    @Override
    public List<Group> getAllGroups(int userId) {
        return groupMap.values().stream()
                .filter(group -> group.getUserIds().contains(userId))
                .map(group -> group.toBuilder().userIds(Collections.unmodifiableList(group.getUserIds())).build())
                .toList();
    }
}
