package com.project.app.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder(toBuilder = true)
@Setter
@ToString
public class User {
    int id;
}
