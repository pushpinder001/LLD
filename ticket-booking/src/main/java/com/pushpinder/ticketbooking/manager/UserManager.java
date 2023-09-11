package com.pushpinder.ticketbooking.manager;

import com.pushpinder.ticketbooking.entity.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserManager implements IUserManager{
    Map<String, User> users;

    public UserManager() {
        this.users = new HashMap<>();
    }

    @Override
    public User registerUser(String name) {
        User user = new User(name);
        this.users.put(name, user);
        return user;
    }
}
