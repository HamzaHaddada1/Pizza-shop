package com.Otto.task.query.repository;

import com.Otto.task.query.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, String> {
}
