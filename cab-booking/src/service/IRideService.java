package service;

import entity.Ride;

import java.util.List;

public interface IRideService {
    Ride bookRide(Integer userId);

    boolean endTrip(Integer rideId);

    List<Ride> getRideHistory(Integer riderId);
}
