package com.pushpinder.ticketbooking.manager;

import com.pushpinder.ticketbooking.entity.UserBookingSession;
import com.pushpinder.ticketbooking.exception.TicketBookingException;
import com.pushpinder.ticketbooking.manager.strategy.IUserBookingSessionExpireStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserBookingSessionManager implements IUserBookingSessionService {
    private int COUNTER = 0;
    private int MAX_RETRY_CNT=0;
    private Map<Integer, UserBookingSession> userBookingSessionMap = new HashMap<>();

    @Autowired
    private ITheaterManager theaterManager;

    @Autowired
    private IUserBookingSessionExpireStrategy userBookingSessionExpireStrategy;

    @Autowired
    private PaymentMangerFactory paymentMangerFactory;

    public UserBookingSession getUserBookingSession(Integer sessionId) {
        if(!this.userBookingSessionMap.containsKey(sessionId)) {
            throw new TicketBookingException("Cannot find userBooking session for sessionId");
        }

        UserBookingSession userBookingSession =  this.userBookingSessionMap.get(sessionId);

        return userBookingSession;
    }

    public UserBookingSession createUserSession(String user, String screenName, String showName) {
        int expireDurationMin = userBookingSessionExpireStrategy.getExpireDuration();
        UserBookingSession userBookingSession = new UserBookingSession(COUNTER++,
                theaterManager.getTheater(), screenName, showName, expireDurationMin);

        this.userBookingSessionMap.put(userBookingSession.getId(), userBookingSession);
        return userBookingSession;
    }

    @Override
    public void selectSeats(Integer sessionId, List<String> seatNames) {
        UserBookingSession userBookingSession = getUserBookingSession(sessionId);
        userBookingSession.addSeats(seatNames);
    }

    @Override
    public void deleteSession(Integer sessionId) {
        UserBookingSession userBookingSession = getUserBookingSession(sessionId);
        userBookingSession.removeSeats();
        this.userBookingSessionMap.remove(sessionId);
    }

    @Override
    public List<String> makePaymentForSession(Integer sessionId, int amount) {
        UserBookingSession userBookingSession = getUserBookingSession(sessionId);

        boolean isSuccess = paymentMangerFactory.getDefaultPaymentMethod()
                .makePayment(amount, userBookingSession);

        List<String> seats = userBookingSession.getBookedSeats();
        if(isSuccess) {
            userBookingSession.updateStatusForPaymentComplete();
            return seats;
        }

        if(userBookingSession.getRetryCount() > MAX_RETRY_CNT) {
            deleteSession(sessionId);
        }

        return List.of();
    }
}
