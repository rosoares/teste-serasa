package com.example.serasa.controller;

import com.example.serasa.dto.pessoa.NovaPessoaDTO;
import com.example.serasa.dto.pessoa.PessoaDTO;
import com.example.serasa.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoa")
@Tag(name = "Pessoa", description = "pessoa API")
public class PessoaController {

    private PessoaService service;

    @Autowired
    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Cadastra nova pessoa")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity post(@RequestBody @Valid NovaPessoaDTO novaPessoaDTO) {
        service.criar(novaPessoaDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    @Operation(summary = "Encontra pessoa pelo id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<PessoaDTO> find(@PathVariable("id") Long id) {

        try {

            return ResponseEntity.ok(service.encontrar(id));

        } catch (NotFoundException e) {

            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping
    @Operation(summary = "Encontra todas as pessoas cadastradas")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<List<PessoaDTO>> get() {

        List<PessoaDTO> resposta = service.encontrarTodas();

        if (resposta.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(resposta);

    }

}
