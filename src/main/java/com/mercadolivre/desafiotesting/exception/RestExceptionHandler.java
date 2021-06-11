package com.mercadolivre.desafiotesting.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        //String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining("\n"));

        return new ResponseEntity<>(fieldsMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RuntimeException.class})
    protected ResponseEntity<Object> handleMethodArgumentNotValid(RuntimeException ex) {
        String msg = ex.getMessage();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        if (ex instanceof NotFoundException) status = HttpStatus.NOT_FOUND;
        if (ex instanceof PropertyNotFoundException) msg = "Propriedade não encontrada.";
        if (ex instanceof DistrictNotFoundException) msg = "Bairro não encontrado.";
        return new ResponseEntity<>(msg, status);
    }

}
