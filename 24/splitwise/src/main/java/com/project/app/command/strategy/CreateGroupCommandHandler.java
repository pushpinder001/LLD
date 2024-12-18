package com.project.app.command.strategy;

import com.project.app.command.ICommandHandler;
import com.project.app.service.GroupService;

import java.util.Arrays;

public class CreateGroupCommandHandler implements ICommandHandler {
    private final GroupService groupService;

    public CreateGroupCommandHandler(GroupService groupService) {
        this.groupService = groupService;
    }

    @Override
    public void execute(String[] params) {
        //TODO::Add validate and convert params to integer
        if(params == null || params.length ==0) {
            System.out.println("Invalid params for command");
            return;
        }
        Integer[] userIds = Arrays.stream(params).map(Integer::parseInt).toArray(Integer[]::new);
        int groupId = groupService.createGroup(userIds);
        System.out.println("Group created with id " + groupId);
    }
}
