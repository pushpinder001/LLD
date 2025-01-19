package com.project.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CronComponent {
    private final CronComponentType cronComponentType;
    private final ICronComponentValue cronComponentValue;
}
