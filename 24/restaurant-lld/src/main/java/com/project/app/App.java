package com.project.app;

import com.project.app.entity.OrderItem;
import com.project.app.repo.IRestaurantRepo;
import com.project.app.repo.IRestaurantRepoImpl;
import com.project.app.service.OrderService;
import com.project.app.service.RestaurantService;
import com.project.app.service.strategy.IHighestRatingSortStrategyImpl;
import com.project.app.service.strategy.IRestaurantSortStrategy;
import com.project.app.service.strategy.LowestPriceRestAssignStrategy;
import com.project.app.type.SelectionCriterion;

import java.util.List;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        IRestaurantRepo restaurantRepo = new IRestaurantRepoImpl();
        List<IRestaurantSortStrategy> restaurantAssignmentStrategies =
                List.of(new LowestPriceRestAssignStrategy(), new IHighestRatingSortStrategyImpl());

        OrderService orderService = new OrderService(restaurantAssignmentStrategies,
                restaurantRepo);
        RestaurantService restaurantService = new RestaurantService(orderService, restaurantRepo);


        //OnBoard Rest
        restaurantService.onBoardRestaurant("R1", 5, 4.5,
                Map.of("Veg Biryani", 100.0, "Paneer Butter Masala", 150.0));

        restaurantService.onBoardRestaurant("R2",5, 4,
                Map.of("Paneer Butter Masala", 175.0, "Idli", 10.0, "Dosa", 50.0,
                        "Veg Biryani", 80.0));

        restaurantService.onBoardRestaurant("R3",1, 4.9,
                Map.of("Gobi Manchurian", 150.0, "Idli", 15.0, "Paneer Butter Masala", 175.0,
                        "Dosa", 30.0));

        //Place Order
        String orderId = orderService.placeOrder("Ashwin", List.of(new OrderItem("Idli", 3),
                new OrderItem("Dosa", 1)), SelectionCriterion.LOWEST_PRICE);
        orderService.placeOrder("Harish", List.of(new OrderItem("Idli", 3),
                new OrderItem("Dosa", 1)), SelectionCriterion.LOWEST_PRICE);
        orderService.placeOrder("Shruthi", List.of(new OrderItem("Veg Biryani", 3))
                , SelectionCriterion.HIGHEST_RATING);

        orderService.markComplete(orderId);

        orderService.placeOrder("Harish", List.of(new OrderItem("Idli", 3),
                new OrderItem("Dosa", 1)), SelectionCriterion.LOWEST_PRICE);

        orderService.placeOrder("XYZ", List.of(new OrderItem("Panner Tikka", 1),
                new OrderItem("Idli", 1)), SelectionCriterion.LOWEST_PRICE);
    }
}
