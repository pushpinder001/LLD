package com.project.splitwise.command.strategy;

import com.project.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateUserCommandHandler implements ICommandHandler {
    @Autowired
    private UserService userService;

    public CreateUserCommandHandler() {

    }

    @Override
    public void execute(String[] arg) {
        int userId = userService.createUser();
        System.out.println("User created with id " + userId);
    }
}
