package com.project.app.dao;

import com.project.app.entity.Booking;
import com.project.app.type.BookingStatus;

import java.util.List;

public interface IBookingRepo {
    Booking save(Booking booking);

    Booking getBookingById(int id);

    List<String> getBookingsByShowIdAndStatus(int showId, BookingStatus bookingStatus);
}
