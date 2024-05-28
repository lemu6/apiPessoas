package com.example.demo.model;

import com.example.demo.model.dto.PessoaDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class PessoaFisica extends Pessoa{
    @Column private String cpf;

    public PessoaFisica(PessoaDto pessoaDto){
        this.nome = pessoaDto.nome();
        this.email = pessoaDto.email();
        this.telefone=pessoaDto.telefone();
        this.cpf = pessoaDto.cpf();
    }

    @Override
    public PessoaDto toDto() {
        return new PessoaDto(this.nome, this.email, this.telefone, this.cpf, null);
    }

    
}
