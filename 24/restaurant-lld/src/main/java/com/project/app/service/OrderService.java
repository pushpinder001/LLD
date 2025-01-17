package com.project.app.service;

import com.project.app.entity.Order;
import com.project.app.entity.OrderItem;
import com.project.app.entity.Restaurant;
import com.project.app.repo.IRestaurantRepo;
import com.project.app.service.strategy.IRestaurantSortStrategy;
import com.project.app.type.OrderStatus;
import com.project.app.type.SelectionCriterion;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderService {
    private final List<Order> orders;
    private final IRestaurantRepo restaurantRepo;
    private final List<IRestaurantSortStrategy> restaurantAssignmentStrategies;

    public OrderService(List<IRestaurantSortStrategy> restaurantSortStrategies,
                        IRestaurantRepo restaurantRepo) {
        this.restaurantAssignmentStrategies = restaurantSortStrategies;
        this.restaurantRepo = restaurantRepo;
        orders = new ArrayList<>();
    }

    public String placeOrder(String user, List<OrderItem> items, SelectionCriterion selectionCriterion) {
        for(IRestaurantSortStrategy restaurantSortStrategy: restaurantAssignmentStrategies) {
            if (restaurantSortStrategy.doYouHandleIt(selectionCriterion)) {
                //Get all restaurants
                List<Restaurant> restaurants = restaurantRepo.getAll();

                List<Restaurant> filteredRest = new ArrayList<>();

                for(var restaurant : restaurants) {
                    //Check all the items are present in rest
                    boolean flag = true;
                    for(var item : items) {
                        if(!restaurant.getMenu().containsKey(item.getItem())) {
                            flag = false;
                            break;
                        }
                    }
                    if(flag) {
                        filteredRest.add(restaurant);
                    }
                }

                List<Restaurant> sortedRest = restaurantSortStrategy.apply(filteredRest, items);

                Order order = createOrder(sortedRest, user, items);
                if(order != null) {
                    return order.getOrderId();
                }
            }
        }
        return null;
    }

    private synchronized Order createOrder(List<Restaurant> restaurants, String user, List<OrderItem> items) {
        for(var restaurant : restaurants) {
            if(restaurant.getMaxOrders() > getCurrentlyAssignedOrders(restaurant.getId())) {
                Order order = new Order(user, items, UUID.randomUUID().toString(), restaurant.getId());
                orders.add(order);
                System.out.println("Order assigned to restaurant " + restaurant.getId());
                return order;
            }
        }

        System.out.println("Order can’t be fulfilled");
        return null;
    }

    public long getCurrentlyAssignedOrders(String rId) {
        return orders.stream().filter(o-> o.getRestId().equals(rId)).filter(o -> o.getStatus() == OrderStatus.ACCEPTED)
                .count();
    }

    public void markComplete(String orderId) {
        Order order = orders.stream().filter(o -> o.getOrderId().equals(orderId)).findAny().orElseThrow(()->
                new RuntimeException("Order Not Found"));

        order.completeOrder();
        System.out.println("Order " + order.getOrderId() + "marked completed");
    }
}
