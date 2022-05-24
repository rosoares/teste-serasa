package com.example.serasa.service.impl;

import com.example.serasa.dto.pessoa.NovaPessoaDTO;
import com.example.serasa.model.Pessoa;
import com.example.serasa.repository.PessoaRepository;
import com.example.serasa.service.PessoaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
public class PessoaServiceImpl implements PessoaService {

    private PessoaRepository repository;
    private ModelMapper mapper;

    @Autowired
    public PessoaServiceImpl(PessoaRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void criar(NovaPessoaDTO novaPessoaDTO) {
        repository.save(novaPessoa(novaPessoaDTO));
    }

    private Pessoa novaPessoa(NovaPessoaDTO novaPessoaDTO) {
        Pessoa pessoa = mapper.map(novaPessoaDTO, Pessoa.class);
        pessoa.setDataInclusao(LocalDate.now());

        return pessoa;
    }
}
