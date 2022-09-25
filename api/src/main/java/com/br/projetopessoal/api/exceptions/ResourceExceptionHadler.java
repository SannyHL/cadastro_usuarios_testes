package com.br.projetopessoal.api.exceptions;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHadler {
    
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> ObjectNotFound(ObjectNotFoundException exception, HttpServletRequest request){
        var error = new StandardError(LocalDateTime.now(ZoneId.of("UTC-3")), HttpStatus.NOT_FOUND.value(), exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        
    }

    @ExceptionHandler(DataIntegratyViolationException.class)
    public ResponseEntity<StandardError> dataIntegratyViolationException(DataIntegratyViolationException exception, HttpServletRequest request){
        var error = new StandardError(LocalDateTime.now(ZoneId.of("UTC-3")), HttpStatus.BAD_REQUEST.value(), exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        
    }
}


