package com.luizalabs.legacyorderapi.repositories;

import com.luizalabs.legacyorderapi.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findByOrderId(int orderId);

    List<Order> findByOrderDateBetween(LocalDate startDate, LocalDate endDate);

    List<Order> findByOrderIdAndOrderDateGreaterThanAndOrderDateLessThan(int orderId, LocalDate startDate, LocalDate enDate);

}
