package com.example.serasa.service;

import com.example.serasa.dto.pessoa.NovaPessoaDTO;
import com.example.serasa.dto.pessoa.PessoaDTO;

import java.util.List;

public interface PessoaService {

    void criar(NovaPessoaDTO novaPessoaDTO);

    PessoaDTO encontrar(Long id);

    List<PessoaDTO> encontrarTodas();

}
