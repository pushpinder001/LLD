package com.project.app.service;

import com.project.app.entity.Item;
import com.project.app.entity.OrderItem;
import com.project.app.entity.Restaurant;
import com.project.app.repo.IRestaurantRepo;

import java.util.*;

public class RestaurantService {
    private final OrderService orderService;
    private final IRestaurantRepo restaurantRepo;

    public RestaurantService(OrderService orderService, IRestaurantRepo restaurantRepo) {
        this.orderService = orderService;
       this.restaurantRepo = restaurantRepo;
    }

    public boolean onBoardRestaurant(String id, int maxOrder, double rating, Map<String, Double> itemToPrice) {
        Restaurant restaurant = new Restaurant(id,
                new HashMap<>(itemToPrice), rating, maxOrder);
        restaurantRepo.add(restaurant);
        return true;
    }

    public boolean addItemToMenu(String itemName, double price) {
        return false;
    }

    public boolean updateItemInMenu(String itemName, double price) {
        return false;
    }

    public Map<Restaurant, Double> getPriceForOrder(List<OrderItem> orderItems) {
        return null;
    }

}
