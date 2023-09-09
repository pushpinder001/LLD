package service;

import dto.Position;
import entity.Driver;
import entity.Ride;
import entity.Rider;

import java.util.List;

public interface ICabBookingService {
    Rider registerRider(Position position);

    Driver registerDriver(Position position);

    boolean updateDriverPosition(Integer driverId, Position position);

    boolean updateDriverAvailability(Integer driverId, boolean isAvail);

    Ride bookRide(Integer userId);

    boolean endTrip(Integer rideId);

    List<Ride> getRideHistory(Integer riderId);
}
