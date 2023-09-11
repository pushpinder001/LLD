package com.pushpinder.ticketbooking.manager.strategy;

import com.pushpinder.ticketbooking.entity.UserBookingSession;

public interface IPricingStrategy {
    int getPriceForUserSession(UserBookingSession userBookingSession);
}
