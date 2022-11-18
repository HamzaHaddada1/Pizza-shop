package com.Otto.task.query.repository;

import com.Otto.task.query.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
