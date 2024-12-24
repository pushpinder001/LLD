package com.project.app.entity;


import com.project.app.type.TripStatus;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
public class Trip {
    private final String id;

    private final String riderId;

    private String driverId;

    private TripStatus tripStatus;

    public Trip(@NonNull final String id,@NonNull final String riderId, @NonNull final String driverId) {
        this.id = id;
        this.riderId = riderId;
        this.tripStatus = TripStatus.DRIVER_ASSIGNED;
        this.driverId = driverId;
    }

    public void completeTrip() {
        if(tripStatus != TripStatus.DRIVER_ASSIGNED) {
            throw new RuntimeException("Invalid Operation on trip with status" + tripStatus);
        }
        tripStatus = TripStatus.COMPLETED;
    }
}
