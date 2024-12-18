package com.project.app.dao.impl;

import com.project.app.dao.IUserDAO;
import com.project.app.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryUserDAO implements IUserDAO {
    Map<Integer, User> userMap = new HashMap<>();

    @Override
    public int save(User user) {
        user.setId(userMap.size());
        userMap.put(user.getId(), user);
        return user.getId();
    }

    @Override
    public boolean containUser(int id) {
        return userMap.containsKey(id);
    }

    @Override
    public List<User> getAllUser() {
        List<Integer> userList = new ArrayList<>();
        return userMap.values().stream()
                .map(user -> User.builder().id(user.getId()).build())
                .toList();
    }
}
