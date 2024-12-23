package com.project.app.entity;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public class Theater {
    final String id;

    final List<Seat> seat;
}
