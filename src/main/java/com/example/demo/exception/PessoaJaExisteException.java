package com.example.demo.exception;


public class PessoaJaExisteException extends Exception{

    public PessoaJaExisteException() {
        super("Pessoa já existe");
    }
    
}
