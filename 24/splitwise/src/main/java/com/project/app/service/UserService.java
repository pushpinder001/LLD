package com.project.app.service;

import com.project.app.dao.IUserDAO;
import com.project.app.entity.User;

public class UserService {
    private IUserDAO userDAO;

    public UserService(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public int createUser() {
        return userDAO.save(new User());
    }

    public boolean isUserPresent(int userId) {
        return userDAO.containUser(userId);
    }

    public void listAllUser() {
        System.out.println(".....Users.....");
        System.out.println(userDAO.getAllUser());
    }
}
