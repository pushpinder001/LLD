package com.pushpinder.ticketbooking.manager;

import com.pushpinder.ticketbooking.entity.UserBookingSession;

public interface IPaymentService {
    boolean makePayment(Integer amount, UserBookingSession userBookingSession);
}
