package io.javabrains.springbootstarter.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(Exception.class)
    public Error handleAllExceptions(Exception e, WebRequest webRequest) {
        return new Error(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    //for request body violations
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleMethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest webRequest) {
        BindingResult result = e.getBindingResult();
        List<String> errors = new ArrayList<>();
        result.getFieldErrors()
                .forEach(fieldError ->
                        errors.add(fieldError.getObjectName() + "." + fieldError.getField() + " : " + fieldError.getDefaultMessage()));
        result.getGlobalErrors()
                .forEach(globalError ->
                        errors.add(globalError.getObjectName() + " : " + globalError.getDefaultMessage()));
        return new Error(HttpStatus.BAD_REQUEST, "Validation failed", errors);
    }

    //for PathVariable etc. violations
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<String> handleConstraintViolationException(ConstraintViolationException e) {
        return e.getConstraintViolations()
                .stream()
                .map(v -> v.getPropertyPath() + " : " + v.getMessage())
                .collect(Collectors.toList());
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
        return new Error(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}
