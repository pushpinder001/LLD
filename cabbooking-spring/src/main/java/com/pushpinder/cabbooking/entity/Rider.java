package com.pushpinder.cabbooking.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Rider {
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String name;

    public Rider(String name) {
        this.name = name;
    }
}
