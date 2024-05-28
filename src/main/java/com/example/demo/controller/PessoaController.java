package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.PessoaJaExisteException;
import com.example.demo.model.dto.PessoaDto;
import com.example.demo.service.PessoaService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/pessoa")
@AllArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<PessoaDto> criarPessoa(@Valid @RequestBody PessoaDto pessoaDto) {
        try {
            PessoaDto novaPessoa = pessoaService.criarPessoa(pessoaDto);
            return ResponseEntity.ok(novaPessoa);
        } catch (PessoaJaExisteException e) {
            return ResponseEntity.badRequest()
                    .body(new PessoaDto("Erro: CPF já cadastrado.", null, null, "ja cadastrado", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/pessoas")
    public ResponseEntity<List<PessoaDto>> listarTodasAsPessoas() {
        List<PessoaDto> pessoas = pessoaService.listarTodasAsPessoas();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{nome}")
    public ResponseEntity<?> listarUmaPessoaPorNome(@PathVariable String nome) {
        Optional<PessoaDto> pessoaOptional = pessoaService.listarUmaPessoaPorNome(nome);
        return pessoaOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
