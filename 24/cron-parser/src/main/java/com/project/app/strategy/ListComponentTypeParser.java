package com.project.app.strategy;

import com.project.app.config.CronComponentIndexToTypeMapping;
import com.project.app.model.CronComponentType;
import com.project.app.model.ICronComponentValue;
import com.project.app.model.ListCronComponentValue;
import com.project.app.model.Range;

import java.util.Arrays;
import java.util.List;

public class ListComponentTypeParser implements IComponentTypeParser {
    @Override
    public boolean isValid(CronComponentType cronComponentType, String cronExpressionComponent) {
        String[] splits = cronExpressionComponent.split(",");
        Range range = CronComponentIndexToTypeMapping.cronComponentTypeRangeMap.get(cronComponentType);

        for(var split : splits) {
            if(Integer.parseInt(split) < range.getMinVal() ||
                Integer.parseInt(split) > range.getMaxVal()) {
                throw new RuntimeException("Invalid Input");
            }
        }

        return true;
    }

    @Override
    public boolean doYouHandleIt(String cronExpressionComponent) {
        return cronExpressionComponent.contains(",");
    }

    @Override
    public ICronComponentValue parse(CronComponentType cronComponentType, String cronExpressionComponent) {
        String[] splits = cronExpressionComponent.split(",");

        List<Integer> vals = Arrays.stream(splits).map(split -> Integer.parseInt(split)).toList();
        ListCronComponentValue listCronComponentValue =  new ListCronComponentValue(vals);
        return listCronComponentValue;
    }
}
