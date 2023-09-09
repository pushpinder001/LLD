package service.impl;

import entity.Driver;
import entity.Ride;
import entity.Rider;
import enums.RideStatus;
import exception.CabBookingException;
import repo.IDriverRepo;
import repo.IRideRepo;
import repo.IRiderRepo;
import service.IDriverAllocationStrategy;
import service.IRideService;

import java.util.List;

public class RideService implements IRideService {
    private IRiderRepo riderRepo;

    private IDriverRepo driverRepo;

    private IRideRepo rideRepo;

    private IDriverAllocationStrategy driverAllocationStrategy;

    public RideService(IRiderRepo riderRepo, IDriverRepo driverRepo, IRideRepo rideRepo, IDriverAllocationStrategy driverAllocationStrategy) {
        this.riderRepo = riderRepo;
        this.driverRepo = driverRepo;
        this.rideRepo = rideRepo;
        this.driverAllocationStrategy = driverAllocationStrategy;
    }

    @Override
    public Ride bookRide(Integer riderId) {
        Rider rider = riderRepo.get(riderId);
        Driver driver = driverAllocationStrategy.getDriver(rider);

        if(rideRepo.getRideByRiderIdAndRideStatus(riderId, RideStatus.IN_PROGRESS).isPresent() ||
                rideRepo.getRideByDriverIdAndRideStatus(driver.getId(), RideStatus.IN_PROGRESS).isPresent()) {
            throw new CabBookingException("The rider is already occupied");
        }

        if(driver != null) {
            driver.setAvailable(false);
            Ride ride = new Ride(rider.getPosition(), riderId, driver.getId(), RideStatus.IN_PROGRESS);
            rideRepo.save(ride);
            return ride;
        }

        return null;
    }

    @Override
    public boolean endTrip(Integer rideId) {
        Ride ride = rideRepo.get(rideId);
        if(ride.getRideStatus() == RideStatus.COMPLETED) {
            throw new CabBookingException("The ride is already completed");
        }
        Rider rider = riderRepo.get(ride.getRiderId());
        Driver driver = driverRepo.get(ride.getDriverId());

        ride.setEndPos(driver.getCabPosition());
        ride.setRideStatus(RideStatus.COMPLETED);
        rideRepo.save(ride);

        rider.setPosition(driver.getCabPosition());
        driver.setAvailable(true);
        return true;
    }

    @Override
    public List<Ride> getRideHistory(Integer riderId) {
        return this.rideRepo.getByRiderId(riderId);
    }

}
