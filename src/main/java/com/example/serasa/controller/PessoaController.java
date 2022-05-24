package com.example.serasa.controller;

import com.example.serasa.dto.pessoa.NovaPessoaDTO;
import com.example.serasa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private PessoaService service;

    @Autowired
    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity post(@RequestBody @Valid NovaPessoaDTO novaPessoaDTO) {
        service.criar(novaPessoaDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
