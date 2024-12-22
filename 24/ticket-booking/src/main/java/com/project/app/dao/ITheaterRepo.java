package com.project.app.dao;

import com.project.app.entity.Show;
import com.project.app.entity.Theater;

import java.util.List;
import java.util.Optional;

public interface ITheaterRepo {
    Theater save(Theater theater);

    Optional<Theater> getById(int id);

    List<Theater> listAllTheater();
}
