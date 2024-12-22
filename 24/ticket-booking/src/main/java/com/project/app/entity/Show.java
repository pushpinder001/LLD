package com.project.app.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class Show {
    int id;

    int theaterId;

    String movieName;

    LocalDateTime startTime;

    LocalDateTime endTime;
}
