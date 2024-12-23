package com.project.app.service;

import com.project.app.entity.Seat;
import com.project.app.entity.Theater;
import lombok.NonNull;

import java.util.*;

public class TheaterService {
    Map<String, Theater> theaterIdToTheaterMapping;

    public TheaterService() {
        theaterIdToTheaterMapping = new HashMap<>();
    }

    public String createTheater(@NonNull final List<Seat> seats) {
        Theater theater = new Theater(UUID.randomUUID().toString(), seats);
        theaterIdToTheaterMapping.put(theater.getId(), theater);
        return theater.getId();
    }

    public List<Seat> getAllSeats(@NonNull final String theaterId) {
        return Collections.unmodifiableList(theaterIdToTheaterMapping.get(theaterId).getSeat());
    }

    public void listAllTheaters() {
        System.out.println(theaterIdToTheaterMapping.values());
    }
}
