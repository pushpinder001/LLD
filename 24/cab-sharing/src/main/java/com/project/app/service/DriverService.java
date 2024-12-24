package com.project.app.service;

import com.project.app.dao.DriverDAO;
import com.project.app.entity.Cab;
import com.project.app.entity.Driver;
import com.project.app.entity.Location;
import lombok.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DriverService {
    DriverDAO driverDAO;
    TripManagementService tripManagementService;

    public DriverService(DriverDAO driverDAO, TripManagementService tripManagementService) {
        this.driverDAO = driverDAO;
        this.tripManagementService = tripManagementService;
    }

    public String registerDriver(@NonNull final String regNo) {
        Driver driver = new Driver(UUID.randomUUID().toString(), new Cab(regNo));
        driverDAO.save(driver);
        return driver.getId();
    }

    public boolean updateCabLocation(@NonNull final String driverId, @NonNull final Location location) throws Exception {
        Driver driver = driverDAO.getDriverById(driverId);
        if(driver == null) {
            throw new Exception("Driver not found");
        }

        return driver.setLocation(location);
    }

    public boolean updateDriverAvail(@NonNull final String driverId, boolean isAvail) throws Exception {
        Driver driver = driverDAO.getDriverById(driverId);
        if(driver == null) {
            throw new Exception("Driver not found");
        }
        if(isAvail) {
            driver.changeAvail(isAvail);
        }
        //TODO:: check if driver is not in trip
        if(tripManagementService.isDriverInTrip(driverId)) {
            throw new Exception("Driver is in trip so status can't be changed to UnAvailable");
        }

        if(!isAvail) {
            driver.changeAvail(isAvail);
        }
        return true;
    }
}
