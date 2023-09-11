package com.pushpinder.ticketbooking.entity;

import com.pushpinder.ticketbooking.exception.TicketBookingException;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class Show {
    private String name;

    private Movie movie;

    private Date startTime;

    private int duration;

    private Map<String, Seat> bookedSeats;

    public Show(String name, Movie movie, Date startTime, int duration) {
        this.name = name;
        this.movie = movie;
        this.startTime = startTime;
        this.duration = duration;
        this.bookedSeats = new HashMap<>();
    }

    public void bookSeats(List<Seat> seats) {
        if(seats.stream().filter(seat -> bookedSeats.containsKey(seat.getName())).findAny().isPresent()) {
            throw new TicketBookingException("Any of selected seats are already booked");
        }

        seats.stream().forEach(k -> bookedSeats.put(k.getName(), k));
    }

    public void unBookSeats(List<Seat> seats) {
        if(seats.stream().filter(seat -> bookedSeats.containsKey(seat.getName())).findAny().isEmpty()) {
            throw new TicketBookingException("Any of selected seats are already booked");
        }

        seats.stream().forEach(k -> bookedSeats.remove(k.getName()));
    }

}
