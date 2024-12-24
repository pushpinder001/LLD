package com.project.app.dao;

import com.project.app.entity.Driver;
import com.project.app.entity.Location;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverDAO {
    Map<String, Driver> driverIdToDrivers;

    public DriverDAO() {
        driverIdToDrivers = new HashMap<>();
    }

    public void save(Driver driver) {
        this.driverIdToDrivers.put(driver.getId(), driver);
    }

    public Driver getDriverById(String driverId) {
        return this.driverIdToDrivers.get(driverId);
    }

    public List<String> getAvailDriver(Location location, double maxDist) {
        return driverIdToDrivers.values().stream().filter(Driver::isAvailable)
                .filter(d -> Location.getDist(d.getLocation(), location)<=maxDist)
                .map(Driver::getId).toList();
    }
}
