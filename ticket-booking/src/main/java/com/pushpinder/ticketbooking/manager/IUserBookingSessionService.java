package com.pushpinder.ticketbooking.manager;

import com.pushpinder.ticketbooking.entity.Seat;
import com.pushpinder.ticketbooking.entity.UserBookingSession;

import java.util.List;

public interface IUserBookingSessionService {

    UserBookingSession getUserBookingSession(Integer sessionId);

    UserBookingSession createUserSession(String user, String screenName, String showName);

    void selectSeats(Integer sessionId, List<String> seatNames);

    void deleteSession(Integer sessionId);

    List<String> makePaymentForSession(Integer sessionId, int amount);
}
