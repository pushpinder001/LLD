package com.project.app.service;

import com.project.app.entity.Seat;
import com.project.app.exception.NonShowPresentException;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

public class AvailabilityService {
    private final ShowService showService;
    private final TheaterService theaterService;
    private final BookingService bookingService;
    private final ILockService lockService;

    public AvailabilityService(ShowService showService, TheaterService theaterService, BookingService bookingService,
                               ILockService lockService) {
        this.showService = showService;
        this.theaterService = theaterService;
        this.bookingService = bookingService;
        this.lockService = lockService;
    }

    public List<String> getSeatAvailForShow(@NonNull final String showId) throws NonShowPresentException {
        final String theaterId = showService.getTheaterIdByForShow(showId);
        //get all seat for theater
        List<Seat> seats = theaterService.getAllSeats(theaterId);

        List<String> seatIds = seats.stream().map(Seat::toString).collect(Collectors.toList());

        List<String> lockedSeatsForShow = getLockedSeat(showId);
        for (var seat : lockedSeatsForShow) {
            seatIds.remove(seat.toString());
        }

        List<String> bookedSeatForShow = bookingService.getBookedSeat(showId);
        for (var seat : bookedSeatForShow) {
            seatIds.remove(seat.toString());
        }

        return seatIds;
    }

    private List<String> getLockedSeat(@NonNull final String showId) {
        return lockService.getLockedKeysWithKeyPrefix(showId);
    }
}
