package com.project.app.service;

import com.project.app.entity.Show;
import com.project.app.exception.NonShowPresentException;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ShowService {
    Map<String, Show> showIdToShowMapping;

    public ShowService() {
        this.showIdToShowMapping = new HashMap<>();
    }

    public String createShow(@NonNull final String theaterId,
                             @NonNull String movieName, @NonNull LocalDateTime startTime,
                             int duration) {
        //TODO:: Add validation
        Show show = new Show(UUID.randomUUID().toString(), theaterId, movieName, startTime, duration);
        this.showIdToShowMapping.put(show.getId(), show);
        return show.getId();
    }

    public String getTheaterIdByForShow(final @NonNull String showId) throws NonShowPresentException {
        return showIdToShowMapping
                .values().stream()
                .filter(show -> show.getId().equals(showId))
                .map(Show::getTheaterId)
                .findFirst().orElseThrow(NonShowPresentException::new);
    }

    public void listAllShows() {
        System.out.println(showIdToShowMapping.values());
    }
}
