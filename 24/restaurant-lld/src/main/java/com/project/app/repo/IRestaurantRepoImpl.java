package com.project.app.repo;

import com.project.app.entity.Restaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IRestaurantRepoImpl implements IRestaurantRepo {
    private final Map<String, Restaurant> restaurantMap;

    public IRestaurantRepoImpl() {
        this.restaurantMap = new HashMap<>();
    }

    @Override
    public void add(Restaurant restaurant) {
        restaurantMap.put(restaurant.getId(), restaurant);
    }

    @Override
    public List<Restaurant> getAll() {
        return new ArrayList<>(restaurantMap.values());
    }
}
