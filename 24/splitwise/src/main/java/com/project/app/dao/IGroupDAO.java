package com.project.app.dao;

import com.project.app.entity.Group;
import com.project.app.entity.User;

import java.util.List;

public interface IGroupDAO{
    int save(Group group);

    boolean containGroup(int id);

    Group getById(int id);

    List<Group> getAllGroups(int userId);
}
