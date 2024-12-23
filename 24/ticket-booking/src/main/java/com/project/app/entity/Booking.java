package com.project.app.entity;

import com.project.app.type.BookingStatus;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public class Booking {
    final String id;

    BookingStatus bookingStatus;

    final String userId;

    final List<String> seatIds;

    final String showId;

    public boolean bookSeat() {
        if( bookingStatus ==  BookingStatus.RESERVED) {
            this.bookingStatus = BookingStatus.BOOKED;
            return true;
        }
        return false;
    }
}
