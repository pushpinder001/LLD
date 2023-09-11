package com.pushpinder.ticketbooking.manager;

import com.pushpinder.ticketbooking.entity.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TheaterManager implements ITheaterManager{
    private Theater theater;

    private TheaterManager() {
        theater = new Theater("Theater");
    }

    @Override
    public boolean addScreen(String name, List<String> seatConfigs) {
        Screen screen = new Screen(name, seatConfigs);
        this.theater.addScreen(screen);
        return true;
    }

    @Override
    public boolean addShow(String screenName, String showName, Movie movie, Date startTime, int duration) {
        this.theater.addShow(screenName, showName, movie, startTime, duration);
        return true;
    }

    @Override
    public Theater getTheater() {
        return theater;
    }

    public List<String> getAvailSeats(String screenName, String showName) {
        return theater.getAvailSeats(screenName, showName);
    }
}
