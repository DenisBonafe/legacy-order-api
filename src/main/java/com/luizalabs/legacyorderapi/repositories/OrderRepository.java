package com.luizalabs.legacyorderapi.repositories;

import com.luizalabs.legacyorderapi.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
