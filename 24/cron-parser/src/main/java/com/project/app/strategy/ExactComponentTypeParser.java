package com.project.app.strategy;

import com.project.app.config.CronComponentIndexToTypeMapping;
import com.project.app.model.CronComponentType;
import com.project.app.model.ICronComponentValue;
import com.project.app.model.Range;
import com.project.app.model.SingleCronComponentValue;

public class ExactComponentTypeParser implements IComponentTypeParser {
    @Override
    public boolean isValid(CronComponentType cronComponentType, String cronExpressionComponent) {
        for(char c : cronExpressionComponent.toCharArray()) {
            if(!Character.isDigit(c)) {
                return false;
            }
        }

        Range range = CronComponentIndexToTypeMapping.cronComponentTypeRangeMap.get(cronComponentType);
        if(range.getMinVal() < Integer.parseInt(cronExpressionComponent) ||
                Integer.parseInt(cronExpressionComponent) > range.getMaxVal()) {
            return false;
        }

        return true;
    }

    @Override
    public boolean doYouHandleIt(String cronExpressionComponent) {
        return !cronExpressionComponent.contains("*") && !cronExpressionComponent.contains(",")
                && !cronExpressionComponent.contains("-");
    }

    @Override
    public ICronComponentValue parse(CronComponentType cronComponentType, String cronExpressionComponent) {
        return new SingleCronComponentValue(Integer.parseInt(cronExpressionComponent));
    }
}
