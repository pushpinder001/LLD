package com.pushpinder.cabbooking.service.impl;;

import com.pushpinder.cabbooking.dto.Position;
import com.pushpinder.cabbooking.entity.Driver;
import com.pushpinder.cabbooking.entity.Ride;
import com.pushpinder.cabbooking.entity.Rider;
import com.pushpinder.cabbooking.enums.RideStatus;
import com.pushpinder.cabbooking.exception.CabBookingException;
import com.pushpinder.cabbooking.repo.IDriverRepo;
import com.pushpinder.cabbooking.repo.IRideRepo;
import com.pushpinder.cabbooking.repo.IRiderRepo;
import com.pushpinder.cabbooking.service.IRideService;
import com.pushpinder.cabbooking.service.strategy.IDriverAllocationStrategy;

import java.util.List;
import java.util.Optional;

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
    public Ride bookRide(Integer riderId, Position startPos, Position endPosition) {
        //TODO:: Check for rider

        Driver driver = driverAllocationStrategy.getDriver(startPos);

        if(rideRepo.getRideByRiderIdAndRideStatus(riderId, RideStatus.IN_PROGRESS).isPresent() ||
                rideRepo.getRideByDriverIdAndRideStatus(driver.getId(), RideStatus.IN_PROGRESS).isPresent()) {
            throw new CabBookingException("The rider is already occupied");
        }

        if(driver != null) {
            driver.setAvailable(false);
            Ride ride = new Ride(startPos, endPosition, riderId, driver.getId(), RideStatus.IN_PROGRESS);
            rideRepo.save(ride);
            return ride;
        }

        return null;
    }

    @Override
    public boolean endTrip(Integer driverId) {
        Ride ride = rideRepo.getRideByDriverIdAndRideStatus(driverId,  RideStatus.IN_PROGRESS).orElseThrow(
                () -> {throw new CabBookingException("The ride is already completed");}
        );

        Driver driver = driverRepo.get(driverId);

        ride.endTrip();
        rideRepo.save(ride);

        driver.setAvailable(true);
        return true;
    }

    @Override
    public List<Ride> getRideHistory(Integer riderId) {
        return this.rideRepo.getByRiderId(riderId);
    }

}
