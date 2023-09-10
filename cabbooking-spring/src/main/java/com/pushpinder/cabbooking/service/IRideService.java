package com.pushpinder.cabbooking.service;

import com.pushpinder.cabbooking.dto.Position;
import com.pushpinder.cabbooking.entity.Ride;

import java.util.List;

public interface IRideService {
    Ride bookRide(Integer userId, Position startPos, Position endPosition);

    boolean endTrip(Integer driverId);

    List<Ride> getRideHistory(Integer riderId);
}
