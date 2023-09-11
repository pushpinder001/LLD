package com.pushpinder.ticketbooking.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class PaymentMangerFactory {

    @Autowired
    private PaymentManger defaultPaymentManager;
    public PaymentManger getDefaultPaymentMethod() {
        return defaultPaymentManager;
    }
}
