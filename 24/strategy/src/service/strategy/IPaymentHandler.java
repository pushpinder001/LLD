package service.strategy;

import dto.IPaymentRequest;
import entity.User;

public interface IPaymentHandler {
    void pay(User user, Double amount, PaymentType paymentType, IPaymentRequest paymentRequest);
}
