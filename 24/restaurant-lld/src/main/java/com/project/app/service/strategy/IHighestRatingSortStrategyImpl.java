package com.project.app.service.strategy;

import com.project.app.entity.OrderItem;
import com.project.app.entity.Restaurant;
import com.project.app.type.SelectionCriterion;

import java.util.Comparator;
import java.util.List;

public class IHighestRatingSortStrategyImpl implements IRestaurantSortStrategy {
    @Override
    public boolean doYouHandleIt(SelectionCriterion selectionCriterion) {
        return selectionCriterion==SelectionCriterion.HIGHEST_RATING;
    }

    @Override
    public List<Restaurant> apply(List<Restaurant> restaurants, List<OrderItem> orderItems) {
        Comparator<Restaurant> cmp = Comparator.comparingDouble(Restaurant::getRating).reversed();
        restaurants.sort(cmp);

        return restaurants;
    }
}
