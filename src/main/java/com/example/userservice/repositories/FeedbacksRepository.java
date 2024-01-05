package com.example.userservice.repositories;

import com.example.userservice.entities.Feedback;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbacksRepository extends JpaRepository<Feedback, Integer> {
    List<Feedback> findFeedbacksByOwnerId(Integer ownerId);
    Feedback save(@NotNull Feedback feedback);
}
