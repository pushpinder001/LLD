package com.project.app.entity;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString(of = {"id", "userIds"})
public class Group {
    private int id;
    private List<Integer> userIds;
}
