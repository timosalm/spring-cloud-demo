package com.example.orderservice.order;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Cacheable("Order")
    @Override
    List<Order> findAll();

    @CacheEvict(cacheNames = "Order", allEntries = true)
    @Override
    <S extends Order> S save(S order);
}