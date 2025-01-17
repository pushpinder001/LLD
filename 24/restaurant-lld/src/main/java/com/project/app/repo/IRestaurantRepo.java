package com.project.app.repo;

import com.project.app.entity.Restaurant;

import java.util.List;

public interface IRestaurantRepo {

    void add(Restaurant restaurant);

    List<Restaurant> getAll();
}
