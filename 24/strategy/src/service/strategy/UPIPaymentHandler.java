package service.strategy;

import dto.IPaymentRequest;
import dto.PaytmPaymentRequest;
import entity.User;

public class UPIPaymentHandler implements IPaymentHandler{
    @Override
    public void pay(User user, Double amount, PaymentType paymentType, IPaymentRequest paymentRequest) {
        PaytmPaymentRequest paytmPaymentRequest = (PaytmPaymentRequest) paymentRequest;
    }
}
