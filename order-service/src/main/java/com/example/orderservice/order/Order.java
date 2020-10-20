package com.example.orderservice.order;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "`Order`") // Order is a reserved word
public class Order implements Serializable { // Serializable required for Redis cache

    private static final long serialVersionUID = -1122620860862785845L;

    @Id
    @GeneratedValue
    private Long id;
    private int productId;
    private OrderStatus orderStatus = OrderStatus.CREATED;

    private String shippingAddress;

    public Order() {
    }

    private Order(int productId, String shippingAddress) {
        this.productId = productId;
        this.shippingAddress = shippingAddress;
    }

    static Order create(int productId, String shippingAddress) {
        return new Order(productId, shippingAddress);
    }

    boolean isValid(List<Product> products) {
        return products.stream().filter(product -> product.getId() == productId).count() == 1;
    }

    void updateStatus(OrderStatusUpdate statusUpdate) {
        if (id.equals(statusUpdate.getId())) {
            this.orderStatus = statusUpdate.getStatus();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
