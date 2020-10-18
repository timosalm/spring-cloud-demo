package com.example.orderservice.order;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "`Order`") // Order is a reserved word
public class Order implements Serializable {

    @Id
    private UUID id = UUID.randomUUID();
    private int productId;
    private OrderStatus orderStatus = OrderStatus.CREATED;

    private String shippingAddress;

    private Order() {
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
