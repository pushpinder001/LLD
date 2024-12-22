package com.project.app.service;

import com.project.app.dao.IBookingRepo;
import com.project.app.entity.Booking;
import com.project.app.entity.Seat;
import com.project.app.type.BookingStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
// SeatId, UserId
public class BookingService {
    IBookingRepo bookingRepo;
    ILockService lockService;
    TheaterService theaterService;
    int ttl = 1 * 60;

    public BookingService(IBookingRepo bookingRepo, ILockService lockService, TheaterService theaterService) {
        this.bookingRepo = bookingRepo;
        this.lockService = lockService;
        this.theaterService = theaterService;
    }

    public Map<String, BookingStatus> getSeatAvailForShow(int showId) {
        //get all seat for theater
        List<Seat> seats = theaterService.getAllSeats(showId);

        Map<String, BookingStatus> seatIdToBookingStatus = new HashMap<>();
        for (var seat : seats) {
            seatIdToBookingStatus.put(seat.toString(), BookingStatus.UNRESERVED);
        }

        List<String> lockedSeatsForShow = getLockedSeat(showId);
        for (var seat : lockedSeatsForShow) {
            seatIdToBookingStatus.put(seat, BookingStatus.RESERVED);
        }

        List<String> bookedSeatForShow = getBookedSeat(showId);
        for (var seat : bookedSeatForShow) {
            seatIdToBookingStatus.put(seat, BookingStatus.BOOKED);
        }

        return seatIdToBookingStatus;
    }

    public Booking reserveSeat(int userId, int showId, String seatId) {
        //TODO:: validate if seat is available
        boolean locked = lockService.getLock("" + showId, seatId, "" + userId, ttl);

        if (!locked) {
            throw new RuntimeException("Seat Unavailable");
        }

        Booking booking = Booking.builder()
                .bookingStatus(BookingStatus.RESERVED)
                .userId(userId)
                .showId(showId)
                .seatIds(List.of(seatId))
                .build();

        return bookingRepo.save(booking);
    }

    public Booking confirmBooking(int bookingId) {
        Booking booking = bookingRepo.getBookingById(bookingId);
        boolean isLockAcquired = lockService.isLockAcquiredWithValue("" + booking.getShowId(),
                "" + booking.getSeatIds().get(0), "" + booking.getUserId(), ttl);

        if(!isLockAcquired) {
            throw new RuntimeException("Unable to book as reservation timedout");
        }

        booking.setBookingStatus(BookingStatus.BOOKED);
        return bookingRepo.save(booking);
    }

    private List<String> getLockedSeat(int showId) {
        return lockService.getLockedKeysWithKeyPrefix("" + showId);
    }

    private List<String> getBookedSeat(int showId) {
        return bookingRepo.getBookingsByShowIdAndStatus(showId, BookingStatus.BOOKED);
    }
}
