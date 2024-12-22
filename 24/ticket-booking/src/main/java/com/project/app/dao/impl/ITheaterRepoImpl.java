package com.project.app.dao.impl;

import com.project.app.dao.ITheaterRepo;
import com.project.app.entity.Theater;

import java.util.*;

public class ITheaterRepoImpl implements ITheaterRepo {
    Map<Integer, Theater> theaterIdToTheater;

    public ITheaterRepoImpl() {
        this.theaterIdToTheater = new HashMap<>();
    }

    @Override
    public Theater save(Theater theater) {
        Theater theater1 = Theater.builder().
                    id(theaterIdToTheater.size())
                    .seat(Collections.unmodifiableList(theater.getSeat())).build();

        theaterIdToTheater.put(theater1.getId(), theater1);
        theater.setId(theater1.getId());
        return theater;
    }

    @Override
    public Optional<Theater> getById(int id) {
        return theaterIdToTheater.values().stream().filter(th -> th.getId() == id)
                .map(th -> th.toBuilder().seat(Collections.unmodifiableList(th.getSeat())).build())
                .findFirst();
    }

    @Override
    public List<Theater> listAllTheater() {
        return theaterIdToTheater.values().stream()
                .map(t -> t.toBuilder().seat(Collections.unmodifiableList(t.getSeat())).build())
                .toList();
    }
}
