package com.project.app.service.strategy;

import com.project.app.entity.OrderItem;
import com.project.app.entity.Restaurant;
import com.project.app.type.SelectionCriterion;

import java.util.List;

public interface IRestaurantSortStrategy {
    boolean doYouHandleIt(SelectionCriterion selectionCriterion);

    List<Restaurant> apply(List<Restaurant> restaurants, List<OrderItem> orderItems
                           );
}
