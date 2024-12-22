package com.project.app.service;

import com.project.app.dao.IUserRepo;
import com.project.app.entity.User;

public class UserService {
    IUserRepo userRepo;

    public UserService(IUserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public int createUser() {
        User user = new User();
        return userRepo.save(user).getId();
    }

    public void listAllUser() {
        System.out.println(userRepo.getAllUsers());
    }
}
