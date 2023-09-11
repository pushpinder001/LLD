package com.pushpinder.ticketbooking.entity;

import com.pushpinder.ticketbooking.exception.TicketBookingException;
import lombok.ToString;

import java.util.*;


@ToString
public class Theater {
    String name;

    Map<String, Screen> screens;

    public Theater(String name) {
        this.name = name;
        this.screens = new HashMap<>();
    }

    private Screen getScreen(String screenName) {
        if(!this.screens.containsKey(screenName)) {
            throw new TicketBookingException("Screen Not Found");
        }
        return this.screens.get(screenName);
    }

    public boolean addScreen(Screen screen) {
        screens.put(screen.getName(), screen);
        return true;
    }

    public boolean addShow(String screenName, String showName, Movie movie, Date startTime, int duration) {
        getScreen(screenName).addShow(showName, movie, startTime, duration);

        return true;
    }

    public void blockSeatsForShow(String screenName, String showName, List<String> blockSeatsForShow) {
        Screen screen = getScreen(screenName);
        screen.blockSeatsForShow(showName, screen.getSeatForSeatNames(blockSeatsForShow));
    }

    public void unBlockSeatsForShow(String screenName, String showName, List<String> blockSeatsForShow) {
        Screen screen = getScreen(screenName);
        screen.unBlockSeatsForShow(showName, screen.getSeatForSeatNames(blockSeatsForShow));
    }

    public List<String> getAvailSeats(String screenName, String showName) {
        Screen screen = getScreen(screenName);
        return screen.getAvailSeats(showName);
    }
}
