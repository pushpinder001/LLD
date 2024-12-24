package com.project.app.service;

import com.project.app.dao.DriverDAO;
import com.project.app.entity.Driver;
import com.project.app.entity.Location;
import com.project.app.entity.Rider;
import com.project.app.entity.Trip;
import com.project.app.type.TripStatus;
import lombok.NonNull;

import java.util.*;
import java.util.stream.Collectors;

public class TripManagementService {
    private final Map<String, Trip> tripIdToTrip;
    private final DriverDAO driverDAO;

    private double MAX_DISTANCE = 10;

    public TripManagementService(DriverDAO driverDAO) {
        this.tripIdToTrip = new HashMap<>();
        this.driverDAO = driverDAO;
    }

    public synchronized String bookCab(@NonNull final String riderId,@NonNull final Location location) throws Exception {
        List<String> driverIds = driverDAO.getAvailDriver(location, MAX_DISTANCE);
        Set<String> driversOnTrip = tripIdToTrip.values().stream().map(Trip::getDriverId).collect(Collectors.toSet());
        Trip trip = null;
        for(var driverId : driverIds) {
            if(!driversOnTrip.contains(driverId)) {
                trip = new Trip(UUID.randomUUID().toString(), riderId, driverId);
            }
        }

        if(trip == null) {
            throw new Exception("Unable to Assign Driver");
        }
        tripIdToTrip.put(trip.getId(), trip);
        return trip.getId();
    }

    public boolean endTrip(@NonNull final String driverId,@NonNull final String tripId) throws Exception {
        Trip trip = tripIdToTrip.get(tripId);

        if(trip == null) {
            throw new Exception("Trip not found");
        }

        //TODO:: validate driver
        if(!trip.getDriverId().equals(driverId)) {
            throw new Exception("Driver unauthorized to end trip");
        }
        trip.completeTrip();
        return true;
    }

    public boolean isDriverInTrip(@NonNull final String driverId) {
        return tripIdToTrip.values().stream().filter(trip -> trip.getTripStatus()== TripStatus.DRIVER_ASSIGNED)
                .anyMatch(trip -> trip.getDriverId().equals(driverId));
    }

    public List<Trip> getTripsByRider(@NonNull final String riderId) {
        return tripIdToTrip.values().stream().filter(trip -> trip.getRiderId().equals(riderId))
                .filter(trip -> trip.getTripStatus()==TripStatus.COMPLETED).toList();
    }
}
