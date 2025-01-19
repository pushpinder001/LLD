package com.project.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ListCronComponentValue implements ICronComponentValue {
    private final List<Integer> valList;
}
