package com.example.orderservice.order;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "`Order`") // Order is a reserved word
public class Order {

    @Id
    private UUID id = UUID.randomUUID();

    public static Order create() {
        return new Order();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
