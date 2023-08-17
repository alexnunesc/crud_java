package com.crud.fullsatckbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UserNotFoundAdvice {
  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.FOUND)
  public Map<String, String> exceptionHandler(UserNotFoundException ex) {
    Map<String, String> map = new HashMap<>();
    map.put("error", ex.getMessage());
    return map;
  }

}
