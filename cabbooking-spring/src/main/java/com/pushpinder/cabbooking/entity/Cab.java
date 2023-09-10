package com.pushpinder.cabbooking.entity;

import com.pushpinder.cabbooking.dto.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class Cab {
    private Position position;
}
