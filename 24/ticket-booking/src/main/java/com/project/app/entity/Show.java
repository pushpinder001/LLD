package com.project.app.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@ToString
public class Show {
    final String id;

    final String theaterId;

    final String movieName;

    final LocalDateTime startTime;

    final int duration;
}
