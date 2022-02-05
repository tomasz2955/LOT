package com.example.LOT;


import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<ExceptionResponse> handleRuntimeException(RuntimeException ex, final HttpServletRequest request) {
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        List<String> errorMessages = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return new ResponseEntity<>(new ExceptionResponse("chce wyswietlic wszystkie exceptiony z error messages jeden po drugim"), HttpStatus.BAD_REQUEST);
    }   //how to collect list of strings into one string java


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException ex, final HttpServletRequest request) {
        logger.info("Udalo sie zarejestrowac user o id asassa  i assas");
        logger.warn("Cos nie wyszlo ale nie szkodzi");
        logger.error(MessageCodes.findByCode(ex.getCode()));
        return new ResponseEntity<>(new ExceptionResponse(ex.getCode()), HttpStatus.NOT_FOUND);
    }
}


class ExceptionResponse {
    private String message;
    private Code code;

    public ExceptionResponse(Code code) {
        this.code = code;
    }

    public ExceptionResponse(String message) {
        this.message = message;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
