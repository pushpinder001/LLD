package com.pushpinder.cabbooking.repo;

import com.pushpinder.cabbooking.entity.Ride;
import com.pushpinder.cabbooking.enums.RideStatus;

import java.util.List;
import java.util.Optional;

public interface IRideRepo {
    boolean save(Ride ride);

    Ride get(Integer id);

    List<Ride> getByRiderId(Integer riderId);

    Optional<Ride> getRideByDriverIdAndRideStatus(Integer driverId, RideStatus rideStatus);

    Optional<Ride> getRideByRiderIdAndRideStatus(Integer riderId, RideStatus rideStatus);
}
