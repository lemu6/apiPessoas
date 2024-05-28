package com.example.demo.converter;

import com.example.demo.model.Pessoa;
import com.example.demo.model.PessoaFisica;
import com.example.demo.model.PessoaJuridica;
import com.example.demo.model.dto.PessoaDto;

public class ConverterFromPessoa {
    public static PessoaDto fromPessoa(Pessoa pessoa) {
        if (pessoa instanceof PessoaFisica) {
            PessoaFisica pessoaFisica = (PessoaFisica) pessoa;
            return new PessoaDto(pessoaFisica.getNome(), pessoaFisica.getEmail(), pessoaFisica.getTelefone(), pessoaFisica.getCpf(), null);
        } else {
            PessoaJuridica pessoaJuridica = (PessoaJuridica) pessoa;
            return new PessoaDto(pessoaJuridica.getNome(), pessoaJuridica.getEmail(), pessoaJuridica.getTelefone(), null, pessoaJuridica.getCnpj());
        }
    }

    
}
