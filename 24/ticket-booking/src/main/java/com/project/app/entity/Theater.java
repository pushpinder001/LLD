package com.project.app.entity;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder(toBuilder = true)
@Setter
@ToString
public class Theater {
    int id;

    List<Seat> seat;
}
