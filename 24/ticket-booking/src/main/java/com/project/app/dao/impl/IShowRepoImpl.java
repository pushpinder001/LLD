package com.project.app.dao.impl;

import com.project.app.dao.IShowRepo;
import com.project.app.entity.Show;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class IShowRepoImpl implements IShowRepo {
    Map<Integer, Show> showIdToShows;

    public IShowRepoImpl() {
        showIdToShows = new HashMap<>();
    }

    @Override
    public Show save(Show show) {
        Show show1 = show.toBuilder().id(showIdToShows.size()).build();
        showIdToShows.put(show1.getId(), show1);
        return show;
    }

    @Override
    public Optional<Show> getById(int id) {
        return showIdToShows.values().stream()
                .filter(s -> s.getId()==id).map(s -> s.toBuilder()
                        .build()).findFirst();
    }

    @Override
    public List<Show> getShows() {
        return showIdToShows.values().stream().map(s -> s.toBuilder().build()).toList();
    }
}
