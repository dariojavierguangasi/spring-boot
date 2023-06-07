package com.rpconsulting.restfull.handlers;

import com.rpconsulting.restfull.dtos.ErrorDto;
import com.rpconsulting.restfull.dtos.ErrorV2Dto;
import com.rpconsulting.restfull.exceptions.AlreadyExistsException;
import com.rpconsulting.restfull.exceptions.AlreadyExistsExceptionV2;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AlreadyExistsException.class})
    public ResponseEntity<?> handleAlreadyExistsException(AlreadyExistsException ex, HttpServletRequest request) {
        ErrorDto error = new ErrorDto();
        error.setMessage(ex.getMessage());
        error.setTime(LocalDateTime.now());
        error.setUrl(request.getRequestURI());
        error.setCode(HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({AlreadyExistsExceptionV2.class})
    public ResponseEntity<?> handleAlreadyExistsV2Exception(AlreadyExistsExceptionV2 ex, HttpServletRequest request) {
        ErrorV2Dto error = new ErrorV2Dto();
        error.setMessages(ex.getErrors());
        error.setTime(LocalDateTime.now());
        error.setUrl(request.getRequestURI());
        error.setCode(HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);

    }

}
