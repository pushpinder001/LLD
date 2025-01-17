package com.project.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
public class Restaurant {
    private final String id;

    private final Map<String, Double> menu;

    private final double rating;

    private final int maxOrders;
}
