package com.project.app.dao;

import com.project.app.entity.User;

import java.util.List;

public interface IUserDAO {
    int save(User user);

    boolean containUser(int id);

    List<User> getAllUser();
}
