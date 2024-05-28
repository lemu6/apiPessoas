package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExcecoesHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public MensagemExceptionDto handleException(Exception e) {
        return new MensagemExceptionDto(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(PessoaJaExisteException.class)
    public MensagemExceptionDto pessoaJaExisteHandler(Exception e) {
        return new MensagemExceptionDto(e.getMessage(), HttpStatus.CONFLICT);
    }
}
