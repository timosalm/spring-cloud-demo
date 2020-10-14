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

    private Order() {}

    private Order(int productId) {
        this.productId = productId;
    }

    public static Order create(int productId) {
        return new Order(productId);
    }

    public boolean isValid(List<Product> products) {
        return products.stream().filter(product -> product.getId() == productId).count() == 1;
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
}
