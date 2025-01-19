package com.project.app.strategy;

import com.project.app.config.CronComponentIndexToTypeMapping;
import com.project.app.model.CronComponentType;
import com.project.app.model.ICronComponentValue;
import com.project.app.model.ListCronComponentValue;
import com.project.app.model.Range;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RangeComponentTypeParser implements IComponentTypeParser{
    @Override
    public boolean isValid(CronComponentType cronComponentType, String cronExpressionComponent) {
        String[] splits = cronExpressionComponent.split("-");
        Range range = CronComponentIndexToTypeMapping.cronComponentTypeRangeMap.get(cronComponentType);

        if(splits.length != 2 || Integer.parseInt(splits[0]) < range.getMinVal() ||
            Integer.parseInt(splits[1]) > range.getMaxVal()) {
            throw new RuntimeException("Invalid Input");
        }

        return true;
    }

    @Override
    public boolean doYouHandleIt(String cronExpressionComponent) {
        return cronExpressionComponent.contains("-");
    }

    @Override
    public ICronComponentValue parse(CronComponentType cronComponentType, String cronExpressionComponent) {
        String[] splits = cronExpressionComponent.split("-");

        List<Integer> vals = new ArrayList<>();
        for(int i=Integer.parseInt(splits[0]); i<= Integer.parseInt(splits[1]); i++ ) {
            vals.add(i);
        }

        ListCronComponentValue listCronComponentValue =  new ListCronComponentValue(vals);
        return listCronComponentValue;
    }
}
