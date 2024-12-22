package com.project.app.dao;

import com.project.app.entity.User;

import java.util.List;

public interface IUserRepo {
    User save(User user);

    List<User> getAllUsers();
}
