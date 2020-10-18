package com.example.shippingservice.shipping;

import java.util.UUID;

class OrderStatusUpdate {

    private UUID id;
    private OrderStatus status;

    private OrderStatusUpdate() {
    }

    private OrderStatusUpdate(UUID id, OrderStatus status) {
        this.id = id;
        this.status = status;
    }

    static OrderStatusUpdate forDelivered(UUID id) {
        return new OrderStatusUpdate(id, OrderStatus.DELIVERED);
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
