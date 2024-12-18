package com.project.splitwise.dao;

import com.project.splitwise.entity.User;

import java.util.List;

public interface IUserDAO {
    int save(User user);

    List<User> getAllUser();
}
