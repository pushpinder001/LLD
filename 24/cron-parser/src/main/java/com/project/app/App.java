package com.project.app;

import com.project.app.model.CronExpression;
import com.project.app.service.CronExpressionService;
import com.project.app.service.CronParserService;
import com.project.app.strategy.AsteriskComponentTypeParser;
import com.project.app.strategy.ExactComponentTypeParser;
import com.project.app.strategy.ListComponentTypeParser;
import com.project.app.strategy.RangeComponentTypeParser;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        CronParserService cronParserService = new CronParserService(
                List.of(new AsteriskComponentTypeParser(), new ExactComponentTypeParser(),
                        new ListComponentTypeParser(), new RangeComponentTypeParser())
        );

        CronExpression cronExpression = cronParserService.parse("*/15 0 1,15 * 1-5");
        System.out.println("Hello World!");

        CronExpressionService cronExpressionService = new CronExpressionService(cronParserService);
        String vv = cronExpressionService.getNextCronExpression("*/15 0 1,15 * 1-5",
                "2024-03-14T00:00:00");

        System.out.println(vv);
    }
}
