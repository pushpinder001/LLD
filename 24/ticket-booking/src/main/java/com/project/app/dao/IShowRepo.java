package com.project.app.dao;

import com.project.app.entity.Show;

import java.util.List;
import java.util.Optional;

public interface IShowRepo {
    Show save(Show show);

    Optional<Show> getById(int id);

    List<Show> getShows();
}
