package com.project.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class CronExpression {
    private final List<CronComponent> cronComponentList;
}
