package com.project.app.service;

import com.project.app.model.CronComponentType;
import com.project.app.model.CronExpression;
import com.project.app.model.ListCronComponentValue;
import com.project.app.model.SingleCronComponentValue;
import com.project.app.strategy.ExactComponentTypeParser;

import java.time.LocalDateTime;

public class CronExpressionService {
    CronParserService cronParserService;

    public CronExpressionService(CronParserService cronParserService) {
        this.cronParserService = cronParserService;
    }

    private boolean isValid(CronExpression cronExpression, LocalDateTime localDateTime) {
        for(var comp : cronExpression.getCronComponentList()) {
            int val = 0;
            if (comp.getCronComponentType() == CronComponentType.MINUTE) {
                val = localDateTime.getMinute();
            } else if (comp.getCronComponentType() == CronComponentType.HOUR) {
                val = localDateTime.getHour();
            } else if (comp.getCronComponentType() == CronComponentType.DAY_OF_THE_MONTH) {
                val = localDateTime.getDayOfMonth();
            } else if (comp.getCronComponentType() == CronComponentType.MONTH) {
                val = localDateTime.getMonthValue();
            } else if (comp.getCronComponentType() == CronComponentType.DAY_OF_WEEK) {
                val = localDateTime.getDayOfWeek().getValue();
            }

            if (comp.getCronComponentValue() instanceof ListCronComponentValue) {
                if (!((ListCronComponentValue) (comp.getCronComponentValue())).getValList().contains(val)) {
                    return false;
                }
            } else {
                if (((SingleCronComponentValue) (comp.getCronComponentValue())).getVal() != val) {
                    return false;
                }
            }
        }

        return true;
    }

    public String getNextCronExpression(String cronExpressionStr, String inputTimeStamp) {
        CronExpression cronExpression = cronParserService.parse(cronExpressionStr);

        LocalDateTime localDateTime = LocalDateTime.parse(inputTimeStamp);

        do {
            localDateTime = localDateTime.plusMinutes(1);
        } while(!isValid(cronExpression, localDateTime));

        return localDateTime.toString();
    }
}
