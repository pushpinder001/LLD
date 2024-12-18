package com.project.app.command.strategy;

import com.project.app.command.ICommandHandler;
import com.project.app.service.UserService;

public class ListAllUserCommandHandler implements ICommandHandler {
    UserService userService;

    public ListAllUserCommandHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(String[] arg) {
        userService.listAllUser();
    }
}
