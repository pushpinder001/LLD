package com.project.app.command.strategy;

import com.project.app.command.ICommandHandler;
import com.project.app.service.UserService;

public class CreateUserCommandHandler implements ICommandHandler {
    private UserService userService;

    public CreateUserCommandHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(String[] arg) {
        int userId = userService.createUser();
        System.out.println("User created with id " + userId);
    }
}
