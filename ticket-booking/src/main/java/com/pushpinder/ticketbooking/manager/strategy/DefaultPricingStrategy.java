package com.pushpinder.ticketbooking.manager.strategy;

import com.pushpinder.ticketbooking.entity.UserBookingSession;
import org.springframework.stereotype.Service;

@Service
public class DefaultPricingStrategy implements IPricingStrategy{
    private int SINGLE_SEAT_PRICE = 100;
    @Override
    public int getPriceForUserSession(UserBookingSession userBookingSession) {
        return userBookingSession.getBookedSeats().size() *SINGLE_SEAT_PRICE ;
    }
}
