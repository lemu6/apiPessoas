package com.example.demo.model;

import com.example.demo.model.dto.PessoaDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@SuperBuilder
public abstract class Pessoa {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) protected Long id;
    @Column protected String nome;
    @Column protected String email;
    @Column protected String telefone;

    public abstract PessoaDto toDto();
    
}
