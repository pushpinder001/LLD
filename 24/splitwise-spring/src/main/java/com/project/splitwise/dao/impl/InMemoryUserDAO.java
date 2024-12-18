package com.project.splitwise.dao.impl;

import com.project.splitwise.dao.IUserDAO;
import com.project.splitwise.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(value="InMemoryUserDAO")
public class InMemoryUserDAO implements IUserDAO {
    Map<Integer, User> userMap = new HashMap<>();

    @Override
    public int save(User user) {
        user.setId(userMap.size());
        userMap.put(user.getId(), user);
        return user.getId();
    }

    @Override
    public List<User> getAllUser() {
        List<Integer> userList = new ArrayList<>();
        return userMap.values().stream()
                .map(user -> User.builder().id(user.getId()).build())
                .toList();
    }
}
