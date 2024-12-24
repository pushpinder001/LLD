package com.project.app;

import com.project.app.dao.DriverDAO;
import com.project.app.entity.Location;
import com.project.app.service.DriverService;
import com.project.app.service.RiderService;
import com.project.app.service.TripManagementService;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        RiderService riderService = new RiderService();

        DriverDAO driverDAO = new DriverDAO();
        TripManagementService tripManagementService = new TripManagementService(driverDAO);
        DriverService driverService = new DriverService(driverDAO, tripManagementService);

        /**
         * Register rider
         */
        String riderId = riderService.registerRider();
        System.out.printf("Rider registered with Id %s%n", riderId);

        String riderId2 = riderService.registerRider();
        System.out.printf("Rider registered with Id %s%n", riderId2);

        /**
         * Register driver
         */

        String driverId = driverService.registerDriver("ABC");
        System.out.printf("Driver registered with Id %s%n", driverId);

        /**
         * Update cab location
         */
        try {
            driverService.updateCabLocation(driverId, new Location(1, 1));
        } catch (Exception e) {
            System.out.println(e);
        }

        /**
         * Update availability
         */
        try {
            driverService.updateDriverAvail(driverId, true);
        } catch (Exception e) {
            System.out.println(e);
        }

        /**
         * Book Cab
         */
        String tripId = null;
        try {
            tripId = tripManagementService.bookCab(riderId, new Location(1, 1));
        } catch (Exception e) {
            System.out.println(e);
        }

        /**
         * Book Cab Rider2
         */
        String tripId2 = null;
        try {
            tripId2 = tripManagementService.bookCab(riderId2, new Location(1, 1));
        } catch (Exception e) {
            System.out.println(e);
        }

        /**
         * End Trip
         */
        try {
            tripManagementService.endTrip(driverId, tripId);
        } catch (Exception e) {
            System.out.println(e);
        }

        /**
         * Fetch Ride History
         */
        System.out.println(tripManagementService.getTripsByRider(riderId));
    }
}
