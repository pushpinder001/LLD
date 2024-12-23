package com.project.app.service;

import com.project.app.entity.Booking;
import com.project.app.type.BookingStatus;
import lombok.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

// SeatId, UserId
public class BookingService {
    final Map<String, Booking> bookingIdToBookingMapping;
    final ILockService lockService;
    final TheaterService theaterService;
    final ShowService showService;
    int ttl = 1 * 60;

    public BookingService(ILockService lockService, TheaterService theaterService,
                          ShowService showService) {
        this.lockService = lockService;
        this.theaterService = theaterService;
        this.showService = showService;
        this.bookingIdToBookingMapping = new HashMap<>();
    }


    public String reserveSeat(@NonNull final String userId, @NonNull final String showId,
                               @NonNull final String seatId) {
        //TODO:: validate if seat is available
        boolean locked = lockService.getLock(showId, seatId,  userId, ttl);

        if (!locked) {
            throw new RuntimeException("Seat Unavailable");
        }

        Booking booking = new Booking(UUID.randomUUID().toString(), BookingStatus.RESERVED,
                userId, List.of(seatId), showId);

        bookingIdToBookingMapping.put(booking.getId(), booking);
        return booking.getId();
    }

    public boolean confirmBooking(@NonNull final String bookingId) {
        //TODO:: validate
        Booking booking = this.bookingIdToBookingMapping.get(bookingId);
        boolean isLockAcquired = lockService.isLockAcquiredWithValue( booking.getShowId(),
                 booking.getSeatIds().get(0), booking.getUserId(), ttl);

        if(!isLockAcquired) {
            return false;
        }

        if(booking.bookSeat()) {
            return false;
        }

        return true;
    }

    public List<String> getBookedSeat(@NonNull final String showId) {
        return bookingIdToBookingMapping.values()
                .stream()
                .filter(b -> b.getShowId().equals(showId) &&
                        b.getBookingStatus() == BookingStatus.BOOKED)
                .flatMap(b -> b.getSeatIds().stream())
                .toList();
    }
}
