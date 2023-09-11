package com.pushpinder.ticketbooking.manager;

import com.pushpinder.ticketbooking.entity.UserBookingSession;
import com.pushpinder.ticketbooking.manager.strategy.IPricingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentManger implements IPaymentService{
    @Autowired
    IPricingStrategy pricingStrategy;

    @Override
    public boolean makePayment(Integer amount, UserBookingSession userBookingSession) {
        userBookingSession.incrementRetryCount();
        //Making paymentLogic
        if(amount != pricingStrategy.getPriceForUserSession(userBookingSession)) {
            return false;
        }

        return true;
    }
}
