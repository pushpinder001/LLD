package com.project.app.service;

import com.project.app.config.CronComponentIndexToTypeMapping;
import com.project.app.model.CronComponent;
import com.project.app.model.CronComponentType;
import com.project.app.model.CronExpression;
import com.project.app.model.ICronComponentValue;
import com.project.app.strategy.IComponentTypeParser;

import java.util.ArrayList;
import java.util.List;

public class CronParserService {
    List<IComponentTypeParser> componentTypeParsers;

    public CronParserService(List<IComponentTypeParser> componentTypeParsers) {
        this.componentTypeParsers = componentTypeParsers;
    }

    public CronExpression parse(String expression) {
        //*/15 0 1,15 * 1-5
        String[] splits = expression.split(" ");

        if(splits.length != 5) {
            throw new RuntimeException("Invalid Input");
        }

        List<CronComponent> cronComponents = new ArrayList<>();

        for(int i=0; i<splits.length; i++) {
            CronComponentType cronComponentType = CronComponentIndexToTypeMapping.cronCompIdxToTypeMapping.get(i);
            IComponentTypeParser  selectedComponentTypeParser = null;
            for(var componentTypeParser : componentTypeParsers) {
                String cronComponentExpression = splits[i];
                if(componentTypeParser.doYouHandleIt(cronComponentExpression)) {
                    selectedComponentTypeParser = componentTypeParser;
                    break;
                }
            }

            if(selectedComponentTypeParser == null || selectedComponentTypeParser.isValid(cronComponentType, splits[i])) {
                ICronComponentValue cronComponentValue = selectedComponentTypeParser.parse(cronComponentType, splits[i]);
                cronComponents.add(new CronComponent(cronComponentType,
                        cronComponentValue));

            } else {
                throw new RuntimeException("Invalid Input");
            }
        }
        return new CronExpression(cronComponents);
    }
}
