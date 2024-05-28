package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.demo.converter.ConverterFromPessoa;
import com.example.demo.exception.PessoaJaExisteException;
import com.example.demo.model.Pessoa;
import com.example.demo.model.dto.PessoaDto;
import com.example.demo.repository.PessoaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaDto criarPessoa(PessoaDto pessoaDto) throws PessoaJaExisteException {
        Pessoa pessoa = pessoaDto.toPessoa();
        try {
            pessoaRepository.save(pessoa);
            return ConverterFromPessoa.fromPessoa(pessoa);
        } catch (DataIntegrityViolationException e) {
            throw new PessoaJaExisteException();
        }
    }
    
    public List<PessoaDto> listarTodasAsPessoas() {
        List<Pessoa> pessoas = pessoaRepository.findAll();
        return pessoas.stream()
                      .map(Pessoa::toDto)
                      .collect(Collectors.toList());
    }

    public Optional<PessoaDto> listarUmaPessoaPorNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            return Optional.empty(); // ou lançar uma exceção, dependendo dos requisitos
        }
    
        try {
            Optional<Pessoa> pessoaOptional = pessoaRepository.findByNome(nome);
            if (pessoaOptional.isPresent()) {
                return pessoaOptional.map(Pessoa::toDto);
            } else {
                return Optional.empty(); // ou lançar uma exceção, dependendo dos requisitos
            }
        } catch (Exception e) {
            // Registrar ou lidar com a exceção, dependendo dos requisitos
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
