package com.pushpinder.cabbooking.service;

import com.pushpinder.cabbooking.dto.Position;
import com.pushpinder.cabbooking.entity.Driver;
import com.pushpinder.cabbooking.entity.Ride;
import com.pushpinder.cabbooking.entity.Rider;

import java.util.List;

public interface ICabBookingService {
    Rider registerRider(String name);

    Driver registerDriver(Position position);

    boolean updateDriverPosition(Integer driverId, Position position);

    boolean updateDriverAvailability(Integer driverId, boolean isAvail);

    Ride bookRide(Integer userId, Position startPos, Position endPosition);

    boolean endTrip(Integer driverId);

    List<Ride> getRideHistory(Integer riderId);
}
