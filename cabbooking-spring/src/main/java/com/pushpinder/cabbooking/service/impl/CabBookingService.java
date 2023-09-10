package com.pushpinder.cabbooking.service.impl;

import com.pushpinder.cabbooking.dto.Position;
import com.pushpinder.cabbooking.entity.Driver;
import com.pushpinder.cabbooking.entity.Ride;
import com.pushpinder.cabbooking.entity.Rider;
import com.pushpinder.cabbooking.repo.IDriverRepo;
import com.pushpinder.cabbooking.repo.IRiderRepo;
import com.pushpinder.cabbooking.service.ICabBookingService;
import com.pushpinder.cabbooking.service.IRideService;

import java.util.List;

public class CabBookingService implements ICabBookingService {
    private IRiderRepo riderRepo;

    private IDriverRepo driverRepo;

    private IRideService rideService;

    public CabBookingService(IRiderRepo riderRepo, IDriverRepo driverRepo, IRideService rideService ) {
        this.riderRepo = riderRepo;
        this.driverRepo = driverRepo;
        this.rideService = rideService;
    }

    @Override
    public Rider registerRider(String name) {
        Rider rider = new Rider(name);
        this.riderRepo.save(rider);
        System.out.println("Registered " + rider);
        return rider;
    }

    @Override
    public Driver registerDriver(Position position) {
        Driver driver = new Driver( position);
        this.driverRepo.save(driver);
        System.out.println("Registered " + driver);
        return driver;
    }

    @Override
    public boolean updateDriverPosition(Integer driverId, Position position) {
        Driver driver = driverRepo.get(driverId);
        driver.updateCabPosition(position);
        System.out.println("Updated Driver position ..... ");
        return true;
    }

    @Override
    public boolean updateDriverAvailability(Integer driverId, boolean isAvail) {
        Driver driver = driverRepo.get(driverId);
        driver.setAvailable(isAvail);
        return true;
    }

    @Override
    public Ride bookRide(Integer riderId, Position startPos, Position endPosition) {
        Ride ride = this.rideService.bookRide(riderId, startPos, endPosition);
        System.out.println("Started ride : " + ride);

        return ride;
    }

    @Override
    public boolean endTrip(Integer driverId) {
        boolean isTripEnded =  this.rideService.endTrip(driverId);
        System.out.println("Successfully ended ride by Driver : " + driverId);
        return isTripEnded;
    }

    @Override
    public List<Ride> getRideHistory(Integer riderId) {
        List<Ride> rides = this.rideService.getRideHistory(riderId);
        System.out.println("Ride History for rider " + riderId);
        System.out.println(rides);
        return rides;
    }


}
