package com.project.app.service;

import com.project.app.dao.IShowRepo;
import com.project.app.dao.ITheaterRepo;
import com.project.app.entity.Seat;
import com.project.app.entity.Show;
import com.project.app.entity.Theater;

import java.util.List;

public class TheaterService {
    ITheaterRepo theaterRepo;
    IShowRepo iShowRepo;

    public TheaterService(ITheaterRepo theaterRepo, IShowRepo iShowRepo) {
        this.theaterRepo = theaterRepo;
        this.iShowRepo = iShowRepo;
    }

    public int createTheater(List<Seat> seats) {
        Theater theater = Theater.builder().seat(seats).build();
        return theaterRepo.save(theater).getId();
    }

    public int createShow(Show show) {
        //TODO:: Add validation
        return iShowRepo.save(show).getId();
    }

    public List<Seat> getAllSeats(int showId) {
        int theaterId = iShowRepo.getById(showId).get().getTheaterId();

        return theaterRepo.getById(theaterId).get().getSeat();
    }

    public void listAllTheaters() {
        System.out.println(theaterRepo.listAllTheater());
    }

    public void listAllShows() {
        System.out.println(iShowRepo.getShows());
    }
}
