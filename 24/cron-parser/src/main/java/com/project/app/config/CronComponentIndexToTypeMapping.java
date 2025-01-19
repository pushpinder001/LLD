package com.project.app.config;

import com.project.app.model.CronComponentType;
import com.project.app.model.Range;

import java.util.HashMap;
import java.util.Map;

public class CronComponentIndexToTypeMapping {
    public static Map<Integer, CronComponentType> cronCompIdxToTypeMapping = new HashMap<>(){{
        put(0, CronComponentType.MINUTE);
        put(1, CronComponentType.HOUR);
        put(2, CronComponentType.DAY_OF_THE_MONTH);
        put(3, CronComponentType.MONTH);
        put(4, CronComponentType.DAY_OF_WEEK);
    }};

    public static Map<CronComponentType, Range> cronComponentTypeRangeMap = new HashMap<>(){{
        put(CronComponentType.MINUTE, new Range(0, 59));
        put(CronComponentType.HOUR, new Range(0, 23));
        put(CronComponentType.DAY_OF_THE_MONTH, new Range(0, 31));
        put(CronComponentType.MONTH, new Range(0, 11));
        put(CronComponentType.DAY_OF_WEEK, new Range(0, 6));
    }};
}
