package service;

import dto.IPaymentRequest;
import entity.User;
import service.strategy.IPaymentHandler;
import service.strategy.PaymentType;

import java.util.Map;

public class PaymentService {
    //Injected via constructor
    Map<PaymentType, IPaymentHandler> paymentTypeToPaymentHandlerMapping;

    public void pay(User user, Double amount, PaymentType paymentType, IPaymentRequest paymentRequest) {
        paymentTypeToPaymentHandlerMapping.get(paymentType).pay(user, amount, paymentType, paymentRequest);
    }
}
