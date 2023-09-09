package repo.impl;

import entity.Ride;
import enums.RideStatus;
import repo.IRideRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryRideRepo implements IRideRepo {
    private static int COUNTER = 0;

    private Map<Integer, Ride> rideIdToDriver;

    public InMemoryRideRepo() {
        this.rideIdToDriver = new HashMap<>();
    }

    @Override
    public boolean save(Ride ride) {
        if(ride.getId() == null) {
            ride.setId(COUNTER++);
        }
        rideIdToDriver.put(ride.getId(), ride);
        return false;
    }

    @Override
    public Ride get(Integer id) {
        return this.get(id);
    }

    @Override
    public List<Ride> getByRiderId(Integer riderId) {
        return rideIdToDriver.values().stream()
                .filter(ride -> ride.getId() == riderId)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Ride> getRideByDriverIdAndRideStatus(Integer driverId, RideStatus rideStatus) {
        return this.rideIdToDriver.values().stream().filter(ride -> ride.getDriverId()==driverId &&
                ride.getRideStatus()==rideStatus).findFirst();
    }

    @Override
    public Optional<Ride> getRideByRiderIdAndRideStatus(Integer riderId, RideStatus rideStatus) {
        return this.rideIdToDriver.values().stream().filter(ride -> ride.getRiderId()==riderId &&
                ride.getRideStatus()==rideStatus).findFirst();
    }

}
