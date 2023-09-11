package com.pushpinder.ticketbooking.manager.strategy;

import com.pushpinder.ticketbooking.entity.UserBookingSession;
import com.pushpinder.ticketbooking.manager.IUserBookingSessionService;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserBookingSessionExpireStrategy implements IUserBookingSessionExpireStrategy {
    private static int EXPIRE_DURATION_IN_MIN = 10;

    @Override
    public int getExpireDuration() {
       return EXPIRE_DURATION_IN_MIN;
    }
}
