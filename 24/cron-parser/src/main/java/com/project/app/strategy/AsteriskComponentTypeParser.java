package com.project.app.strategy;

import com.project.app.config.CronComponentIndexToTypeMapping;
import com.project.app.model.CronComponentType;
import com.project.app.model.ICronComponentValue;
import com.project.app.model.ListCronComponentValue;
import com.project.app.model.Range;

import java.util.ArrayList;
import java.util.List;

public class AsteriskComponentTypeParser implements IComponentTypeParser{
    @Override
    public boolean isValid(CronComponentType cronComponentType, String cronExpressionComponent) {
        Range range = CronComponentIndexToTypeMapping.cronComponentTypeRangeMap.get(cronComponentType);

        String[] splits = cronExpressionComponent.split("/");
        if(!splits[0].equals("*")) {
            return false;
        }
        return true;
    }

    @Override
    public boolean doYouHandleIt(String cronExpressionComponent) {
        return cronExpressionComponent.contains("*");
    }

    @Override
    public ICronComponentValue parse(CronComponentType cronComponentType, String cronExpressionComponent) {
        String[] splits = cronExpressionComponent.split("/");
        Range range = CronComponentIndexToTypeMapping.cronComponentTypeRangeMap.get(cronComponentType);

        int interval = splits.length == 1? 1 : Integer.parseInt(splits[1]);
        List<Integer> vals = new ArrayList<>();

        for(int i=0; i<=range.getMaxVal(); i+=interval) {
            vals.add(i);
        }

        return new ListCronComponentValue(vals);
    }
}
