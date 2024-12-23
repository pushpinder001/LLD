package com.project.app;

import com.project.app.entity.Seat;
import com.project.app.exception.NonShowPresentException;
import com.project.app.service.*;
import com.project.app.service.impl.LockServiceInMemoryMapImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws NonShowPresentException {
        /**
         * Adding Users users
         */
        UserService userService = new UserService();
        String uId1 = userService.createUser();
        String uId2 = userService.createUser();


        /**
         * Adding theaters and shows
         */
        TheaterService theaterService = new TheaterService();
        String tId = theaterService.createTheater(List.of(new Seat('A', 1), new Seat('B', 1)));

        ShowService showService = new ShowService();
        String sId = showService.createShow(tId, "ABC", LocalDateTime.now(), 3);
        //List All Users
        userService.listAllUser();

        //List All theaters
        theaterService.listAllTheaters();

        //List All Shows
        showService.listAllShows();

        /**
         * Get all seat of show
         */
        ILockService lockService = new LockServiceInMemoryMapImpl();
        BookingService bookingService = new BookingService(lockService, theaterService, showService);

        //Get availSeats
        AvailabilityService availabilityService = new AvailabilityService(showService, theaterService, bookingService,
                lockService);
        List<String> availSeats =  availabilityService.getSeatAvailForShow(sId);
        System.out.println(availSeats);

        /**
         *
         */
        String bId = bookingService.reserveSeat(uId1, sId, "A1");
        System.out.println(bId);

        boolean success = bookingService.confirmBooking(bId);
        System.out.println(success);

        var availSeats1 = availabilityService.getSeatAvailForShow(sId);
        System.out.println(availSeats1);
    }
}
