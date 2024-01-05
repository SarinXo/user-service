package com.example.userservice.handlers;

import com.example.userservice.handlers.exceptions.UserError;
import com.example.userservice.handlers.exceptions.WeightError;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserError.class)
    public ModelAndView handleUserError(RuntimeException e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", e.getMessage());
        modelAndView.addObject("errorCode", HttpStatus.BAD_REQUEST.value());
        modelAndView.setViewName("error-page");
        return modelAndView;
    }
    @ExceptionHandler(WeightError.class)
    public ModelAndView handleWeightError(RuntimeException e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", e.getMessage());
        modelAndView.addObject("errorCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
        modelAndView.setViewName("error-page");
        return modelAndView;
    }

}
