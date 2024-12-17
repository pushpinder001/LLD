package service.strategy;

import dto.IPaymentRequest;
import entity.User;

public class PaytmPaymentHandler implements IPaymentHandler{
    @Override
    public void pay(User user, Double amount, PaymentType paymentType, IPaymentRequest paymentRequest) {

    }
}
