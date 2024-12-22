package com.project.app.entity;

import com.project.app.type.BookingStatus;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
@ToString
public class Booking {
    Integer id;

    BookingStatus bookingStatus;

    int userId;

    int paymentId;

    List<String> seatIds;

    int showId;
}
