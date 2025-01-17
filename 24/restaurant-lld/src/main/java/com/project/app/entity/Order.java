package com.project.app.entity;

import com.project.app.type.OrderStatus;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Order {
    private final String orderId;

    private OrderStatus status;

    private final List<OrderItem> orderItems;

    private final String user;

    private String restId;

    public Order(@NonNull final String user,@NonNull final List<OrderItem> orderItems,
                 @NonNull final String orderId , @NonNull final String rId
        ) {
        this.user = user;
        this.orderItems = Collections.unmodifiableList(new ArrayList<>(orderItems));
        this.status = OrderStatus.ACCEPTED;
        this.orderId = orderId;
        this.restId = rId;

    }

    public boolean completeOrder() {
        if(status == OrderStatus.CREATED) {
            this.status = OrderStatus.COMPLETED;
            return true;
        }

        return false;
    }
}
