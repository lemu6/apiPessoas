package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public record MensagemExceptionDto(
    String mensagem,
    HttpStatus status
) {
    
}
