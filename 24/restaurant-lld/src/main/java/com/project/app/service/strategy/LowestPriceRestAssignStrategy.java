package com.project.app.service.strategy;

import com.project.app.entity.OrderItem;
import com.project.app.entity.Restaurant;
import com.project.app.repo.IRestaurantRepo;
import com.project.app.service.RestaurantService;
import com.project.app.type.SelectionCriterion;

import java.util.*;

public class LowestPriceRestAssignStrategy implements IRestaurantSortStrategy {

    public LowestPriceRestAssignStrategy() {
    }

    @Override
    public boolean doYouHandleIt(SelectionCriterion selectionCriterion) {
        return selectionCriterion==SelectionCriterion.LOWEST_PRICE;
    }

    @Override
    public List<Restaurant> apply(List<Restaurant> restaurants, List<OrderItem> orderItems) {
        Map<Restaurant, Double> restsToOrderPrice = new HashMap<>();
        for(var restaurant: restaurants) {
            double tPrice = 0;

            for(var item : orderItems) {
                tPrice += (restaurant.getMenu().get(item.getItem())*item.getQuantity());
            }

            restsToOrderPrice.put(restaurant, tPrice);
        }

        Comparator<Restaurant> comparator = Comparator.comparingDouble(restsToOrderPrice::get);
        restaurants.sort(comparator);
        return restaurants;
    }

}
