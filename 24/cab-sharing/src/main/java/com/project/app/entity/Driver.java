package com.project.app.entity;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class Driver {
    private final String id;

    private Location location;

    private final Cab cab;

    private boolean isAvailable;

    public Driver(String id, Cab cab) {
        this.id = id;
        this.cab = cab;
        this.isAvailable = false;
    }

    public boolean setLocation(@NonNull final Location updatedLocation) {
        location = updatedLocation;
        return true;
    }

    public boolean changeAvail(boolean updatedAvail) {
        isAvailable = updatedAvail;
        return true;
    }
}
