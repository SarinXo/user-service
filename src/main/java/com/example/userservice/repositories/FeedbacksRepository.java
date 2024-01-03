package com.example.userservice.repositories;

import com.example.userservice.dto.FeedbackDto;
import com.example.userservice.entities.Feedback;
import com.example.userservice.services.FeedbacksService;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;

import java.util.List;

public interface FeedbacksRepository extends JpaRepository<Feedback, Integer> {
    List<Feedback> findFeedbacksByOwnerId(Integer ownerId);
    Feedback save(@NotNull Feedback feedback);
}
