package com.project.app.dao.impl;

import com.project.app.dao.IBookingRepo;
import com.project.app.entity.Booking;
import com.project.app.type.BookingStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryBookingRepo implements IBookingRepo {
    Map<Integer, Booking> bookingIdToBookingMap;

    public InMemoryBookingRepo() {
        this.bookingIdToBookingMap = new HashMap<>();
    }

    @Override
    public Booking save(Booking booking) {
        Booking booking1 = booking.toBuilder().build();
        if(booking.getId() == null) {
            booking1.setId(bookingIdToBookingMap.size());
        }
        bookingIdToBookingMap.put(booking1.getId(), booking1);
        booking.setId(booking1.getId());
        return booking;
    }

    @Override
    public Booking getBookingById(int id) {
        return bookingIdToBookingMap.get(id).toBuilder().build();
    }

    @Override
    public List<String> getBookingsByShowIdAndStatus(int showId, BookingStatus bookingStatus) {
        return bookingIdToBookingMap.values().stream().filter(b -> b.getShowId() == showId &&
                        bookingStatus == BookingStatus.BOOKED).map(Booking::getSeatIds).
                flatMap(List::stream).toList();
    }
}
