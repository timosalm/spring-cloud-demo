package com.example.orderservice.order;

import java.util.UUID;

class OrderStatusUpdate {

    private UUID id;
    private OrderStatus status;

    private OrderStatusUpdate() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
