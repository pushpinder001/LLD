package com.project.app.strategy;

import com.project.app.model.CronComponentType;
import com.project.app.model.ICronComponentValue;

public interface IComponentTypeParser {
    boolean isValid(CronComponentType cronComponentType, String cronExpressionComponent);

    boolean doYouHandleIt(String cronExpressionComponent);

    ICronComponentValue parse(CronComponentType cronComponentType, String cronExpressionComponent);
}
