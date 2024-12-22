package com.project.app;

import com.project.app.dao.impl.IShowRepoImpl;
import com.project.app.dao.impl.ITheaterRepoImpl;
import com.project.app.dao.impl.IUserRepoImpl;
import com.project.app.dao.impl.InMemoryBookingRepo;
import com.project.app.entity.Booking;
import com.project.app.entity.Seat;
import com.project.app.entity.Show;
import com.project.app.service.*;
import com.project.app.service.impl.LockServiceInMemoryMapImpl;
import com.project.app.type.BookingStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        /**
         * Adding Users users
         */
        UserService userService = new UserService(new IUserRepoImpl());
        int uId1 = userService.createUser();
        int uId2 = userService.createUser();


        /**
         * Adding theaters and shows
         */
        TheaterService theaterService = new TheaterService(new ITheaterRepoImpl(), new IShowRepoImpl());
        int tId = theaterService.createTheater(List.of(new Seat('A', 1), new Seat('B', 1)));

        int sId = theaterService.createShow(new Show().toBuilder()
                .theaterId(tId).movieName("ABC").startTime(LocalDateTime.now()).endTime(LocalDateTime.now().plusHours(3)).build());
        //List All Users
        userService.listAllUser();

        //List All theaters
        theaterService.listAllTheaters();

        //List All Shows
        theaterService.listAllShows();

        /**
         * Get all seat of show
         */
        ILockService lockService = new LockServiceInMemoryMapImpl();
        BookingService bookingService = new BookingService(new InMemoryBookingRepo(), lockService, theaterService);
        Map<String, BookingStatus> bIdToStatusMapping =  bookingService.getSeatAvailForShow(sId);
        System.out.println(bIdToStatusMapping);

        /**
         *
         */
        Booking booking = bookingService.reserveSeat(uId1, sId, "A1");
        System.out.println(booking);

        Booking booking2 = bookingService.confirmBooking(booking.getId());
        System.out.println(booking2);

        var map = bookingService.getSeatAvailForShow(sId);
        System.out.println(map);
    }
}
