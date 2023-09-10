package com.pushpinder.cabbooking.entity;

import com.pushpinder.cabbooking.dto.Position;
import com.pushpinder.cabbooking.enums.RideStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Ride {
    @Getter
    @Setter
    private Integer id;

    @Getter
    private Position startPos;

    @Getter
    private Position endPos;

    @Getter
    private Integer riderId;

    @Getter
    private Integer driverId;

    @Getter
    private RideStatus rideStatus;

    public Ride(Position startPos, Position endPos, Integer riderId, Integer driverId, RideStatus rideStatus) {
        this.startPos = startPos;
        this.endPos = endPos;
        this.riderId = riderId;
        this.driverId = driverId;
        this.rideStatus = rideStatus;
    }

    public void endTrip() {
        this.rideStatus = RideStatus.COMPLETED;
    }
}
