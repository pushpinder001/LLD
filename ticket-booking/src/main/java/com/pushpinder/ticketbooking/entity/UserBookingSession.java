package com.pushpinder.ticketbooking.entity;

import com.pushpinder.ticketbooking.enums.UserBookingSessionStatus;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class UserBookingSession {
    private static int COUNTER = 0;

    @Getter
    private Integer id;

    private Integer userId;

    private UserBookingSessionStatus userBookingSessionStatus;

    private Date expireTime;

    @Getter
    private int retryCount;

    @Getter
    private Theater theater;

    private String screenName;

    private String showName;

    @Getter
    private List<String> bookedSeats;

    public UserBookingSession(Integer userId, Theater theater, String screenName, String showName, int durationMin) {
        this.userId = userId;
        this.theater = theater;
        this.screenName = screenName;
        this.showName = showName;
        this.bookedSeats = new ArrayList<>();
        this.expireTime = new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(durationMin));
        this.userBookingSessionStatus = UserBookingSessionStatus.TEMP_BLOCKED;
        this.userId = COUNTER++;
    }

    public void addSeats(List<String> seats) {
        theater.blockSeatsForShow(screenName, showName, seats);
        this.bookedSeats.addAll(seats);
    }

    public void removeSeats() {
        theater.unBlockSeatsForShow(screenName, showName, this.bookedSeats);
    }

    public void incrementRetryCount() {
        retryCount++;
    }


    public void updateStatusForPaymentComplete() {
        this.userBookingSessionStatus = UserBookingSessionStatus.BOOKED;
    }
}
