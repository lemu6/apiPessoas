package com.example.demo.model.dto;

import com.example.demo.model.Pessoa;
import com.example.demo.model.PessoaFisica;
import com.example.demo.model.PessoaJuridica;

import jakarta.validation.constraints.NotBlank;

public record PessoaDto(
    @NotBlank String nome,
    String email,
    String telefone,
    String cpf,
    String cnpj
) {
    public Pessoa toPessoa() {
       if( cpf != null ) {
           return new PessoaFisica(this);
       } else {
           return new PessoaJuridica(this);
       }
    }
    
}
