package com.project.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SingleCronComponentValue implements ICronComponentValue{
    private final int val;
}
