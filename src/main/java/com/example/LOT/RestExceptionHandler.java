package com.example.LOT;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
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
                .map((FieldError fieldError) -> fieldError.getField() + " - " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        return new ResponseEntity<>(new ExceptionResponse(errorMessages), HttpStatus.BAD_REQUEST);
    }


//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @ExceptionHandler(UserNotFoundException.class)
//    public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException ex, final HttpServletRequest request) {
//        return new ResponseEntity<>(new ExceptionResponse(ex.getCode()), HttpStatus.NOT_FOUND);
//    }
}


class ExceptionResponse {
    private String message;
    private List<String> fieldValidationMessages;
    private Code code;

    public ExceptionResponse(Code code) {
        this.code = code;
    }

    public ExceptionResponse(String message) {
        this.message = message;
    }

    public ExceptionResponse(List<String> fieldValidationMessages) {
        this.fieldValidationMessages = fieldValidationMessages;
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

    public List<String> getFieldValidationMessages() {
        return fieldValidationMessages;
    }

    public void setFieldValidationMessages(List<String> fieldValidationMessages) {
        this.fieldValidationMessages = fieldValidationMessages;
    }
}
