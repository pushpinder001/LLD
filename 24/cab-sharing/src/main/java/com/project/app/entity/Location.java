package com.project.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Location {
    private int x;

    private int y;

    public static double getDist(Location locationA, Location locationB) {
        return Math.sqrt(Math.pow(locationA.x-locationB.x, 2) + Math.pow(locationA.y-locationB.y, 2));
    }
}
