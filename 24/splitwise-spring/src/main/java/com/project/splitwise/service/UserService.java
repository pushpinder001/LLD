package com.project.splitwise.service;

import com.project.splitwise.dao.IUserDAO;
import com.project.splitwise.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    @Qualifier("InMemoryUserDAO")
    private IUserDAO userDAO;

    public UserService() {
    }

    public int createUser() {
        int id = userDAO.save(new User());
        return id;
    }

    public boolean isUserPresent(int userId) {
        return false;
    }

    public void listAllUser() {
        System.out.println("List All users");
    }
}
