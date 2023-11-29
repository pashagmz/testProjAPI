package com.kulbitski.restaurant.configs;

import com.kulbitski.restaurant.dto.ExceptionDto;
import com.kulbitski.restaurant.exceptions.RestaurantNotFoundException;
import java.util.Collections;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ExceptionDto handleException(Exception ex) {
        return new ExceptionDto(ex.getMessage(), ex.getClass().getSimpleName());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RestaurantNotFoundException.class)
    @ResponseBody
    public ExceptionDto handleException(RestaurantNotFoundException ex) {
        return new ExceptionDto(ex.getMessage(), ex.getClass().getSimpleName());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ExceptionDto handleException(IllegalArgumentException ex) {
        return new ExceptionDto(ex.getMessage(), ex.getClass().getSimpleName());
    }
}
