package com.pushpinder.ticketbooking.manager;

import com.pushpinder.ticketbooking.entity.Movie;
import com.pushpinder.ticketbooking.entity.Theater;

import java.util.Date;
import java.util.List;

public interface ITheaterManager {
    boolean addScreen(String name, List<String> seats);

    boolean addShow(String screenName, String showName, Movie movie, Date startTime, int duration);

    Theater getTheater();

    List<String> getAvailSeats(String screenName, String showName);
}
