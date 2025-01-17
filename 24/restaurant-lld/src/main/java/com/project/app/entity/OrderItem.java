package com.project.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderItem {
    private final String item;

    private final int quantity;
}
