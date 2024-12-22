package com.project.app.dao.impl;

import com.project.app.dao.IUserRepo;
import com.project.app.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IUserRepoImpl implements IUserRepo {
    Map<Integer, User> userIdToUserMap;

    public IUserRepoImpl() {
        this.userIdToUserMap = new HashMap<>();
    }

    @Override
    public User save(User user) {
        User user1 = user.toBuilder().id(userIdToUserMap.size()).build();
        this.userIdToUserMap.put(user1.getId(), user1);
        user.setId(user1.getId());
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userIdToUserMap.values().stream().map(u -> u.toBuilder().build()).toList();
    }
}
