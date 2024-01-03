package com.example.userservice.handlers;

import com.example.userservice.handlers.exceptions.UserError;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserError.class)
    public ModelAndView handleRuntimeException(RuntimeException e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", e.getMessage());
        modelAndView.addObject("errorCode", 400);
        modelAndView.setViewName("error-page");
        return modelAndView;
    }

}
