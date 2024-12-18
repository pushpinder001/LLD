package com.project.app.command.strategy;

import com.project.app.command.ICommandHandler;
import com.project.app.service.GroupService;

public class ListAllGroupCommandHandler implements ICommandHandler {
    GroupService groupService;

    public ListAllGroupCommandHandler(GroupService groupService) {
        this.groupService = groupService;
    }

    @Override
    public void execute(String[] params) {
        //TODO::Add validate and convert params to integer
        if(params == null || params.length ==0) {
            System.out.println("Invalid params for command");
            return;
        }

        Integer userId = Integer.parseInt(params[0]);

        groupService.listAllGroups(userId);
    }
}
