package com.project.app.service;

import com.project.app.entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserService {
    Map<String, User> userIdToUserMapping;

    public UserService() {
        this.userIdToUserMapping = new HashMap<>();
    }

    public String createUser() {
        User user = new User(UUID.randomUUID().toString());
        this.userIdToUserMapping.put(user.getId(), user);
        return user.toString();
    }

    public void listAllUser() {
        System.out.println(userIdToUserMapping.values());
    }
}
