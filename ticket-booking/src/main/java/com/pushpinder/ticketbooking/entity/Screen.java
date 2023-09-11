package com.pushpinder.ticketbooking.entity;

import com.pushpinder.ticketbooking.exception.TicketBookingException;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public class Screen {
    @Getter
    String name;

    Map<String, Show> shows;

    HashMap<String, Seat> seats;

    public Screen(String name, List<String> seats) {
        this.name = name;
        this.seats = new HashMap<>();
        seats.stream().forEach(k -> this.seats.put(k, new Seat(k)));
        this.shows = new HashMap<>();
    }

    public void addShow(String showName, Movie movie, Date startTime, int duration) {
        if(this.shows.containsKey(showName)) {
            throw new TicketBookingException("Show with the name already present");
        }
        //TODO:: Add validation for time overlap
        this.shows.put(showName, new Show(showName, movie, startTime, duration));
    }

    private Show getShow(String showName) {
        if(!this.shows.containsKey(showName)) {
            throw new TicketBookingException("Show not found");
        }

        return this.shows.get(showName);
    }

    public void blockSeatsForShow(String showName, List<Seat> blockSeatsForShow) {
        getShow(showName).bookSeats(blockSeatsForShow);
    }

    public void unBlockSeatsForShow(String showName, List<Seat> blockSeatsForShow) {
        getShow(showName).unBookSeats(blockSeatsForShow);
    }

    public List<Seat> getSeatForSeatNames(List<String> seatNames) {
        //TODO:: Add validation
        return seatNames.stream().map(seatName -> this.seats.get(seatName)).collect(Collectors.toList());
    }

    public List<String> getAvailSeats(String showName) {
        Show show = getShow(showName);
        Set<String> bookedSeats = show.getBookedSeats().keySet();

        return seats.keySet().stream().filter(seat -> !bookedSeats.contains(seat)).collect(Collectors.toList());
    }
}
