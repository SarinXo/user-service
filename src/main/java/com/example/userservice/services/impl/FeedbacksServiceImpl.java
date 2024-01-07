package com.example.userservice.services.impl;

import com.example.userservice.dto.FeedbackDto;
import com.example.userservice.entities.Feedback;
import com.example.userservice.entities.User;
import com.example.userservice.handlers.exceptions.FeedbackError;
import com.example.userservice.repositories.FeedbacksRepository;
import com.example.userservice.repositories.UserRepository;
import com.example.userservice.services.FeedbacksService;
import com.example.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class FeedbacksServiceImpl implements FeedbacksService {
    private final FeedbacksRepository feedbacksRepository;
    private final UserService userService;
    private final static List<String> BAD_WORDS = List.of("бяка", "кака", "бака", "жук навозный");
    @Override
    public List<Feedback> findFeedbacksByFarmerId(Integer id) {
        return feedbacksRepository.findFeedbacksByOwnerId(id);
    }

    @Override
    public Feedback addFeedBack(FeedbackDto feedbackDto) {

        if(isInappropriateMessage(feedbackDto.getComment())){
            throw new FeedbackError("Your comment contains bad word(s)!");
        }

        String login = ((UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername();

        User user = userService.getUserByLogin(login);
        Feedback feedback = new Feedback(
                feedbackDto.getComment(),
                feedbackDto.getToFarmerWithId(),
                feedbackDto.getRating(),
                LocalDate.now(),
                user.getFarmerId()
        );
        return feedbacksRepository.save(feedback);
    }

    private boolean isInappropriateMessage(String message){
        String lowerCaseMessage = message.toLowerCase();
        for (String badWord : BAD_WORDS) {
            if (lowerCaseMessage.matches(".*\\b" + Pattern.quote(badWord) + "\\b.*")) {
                return true;
            }
        }
        return false;
    }
}
