package com.project.app.service;

import com.project.app.entity.Rider;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RiderService {
    Map<String, Rider> riderIdToRider;

    public RiderService() {
        riderIdToRider = new HashMap<>();
    }

    public String registerRider() {
        Rider rider = new Rider(UUID.randomUUID().toString());
        riderIdToRider.put(rider.getId(), rider);
        return rider.getId();
    }
}
