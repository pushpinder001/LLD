package com.pushpinder.cabbooking.entity;

import com.pushpinder.cabbooking.dto.Position;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Driver {

    @Getter
    @Setter
    private Integer id;

    private Cab cab;

    @Getter
    @Setter
    private boolean isAvailable;

    public Driver(Position position) {
        this.cab = new Cab(position);
        this.isAvailable = true;
    }

    public Position getCabPosition() {
        return this.cab.getPosition();
    }

    public boolean updateCabPosition(Position position) {
        cab.setPosition(position);
        return true;
    }
}
