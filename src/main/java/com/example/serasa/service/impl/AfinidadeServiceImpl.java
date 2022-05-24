package com.example.serasa.service.impl;

import com.example.serasa.dto.afinidade.AfinidadeDTO;
import com.example.serasa.model.Afinidade;
import com.example.serasa.repository.AfinidadeRepository;
import com.example.serasa.service.AfinidadeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AfinidadeServiceImpl implements AfinidadeService {

    private AfinidadeRepository repository;

    private ModelMapper mapper;

    @Autowired
    public AfinidadeServiceImpl(AfinidadeRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void criar(AfinidadeDTO afinidadeDTO) {

        Afinidade afinidade = new Afinidade();
        afinidade.setRegiao(afinidadeDTO.getRegiao());
        afinidade.setEstados(afinidadeDTO.getEstados());

        repository.save(afinidade);

    }

    @Override
    public AfinidadeDTO encontraPelaRegiao(String regiao) {

        Afinidade afinidade = repository.findByRegiao(regiao);

        if (afinidade == null) {
            return new AfinidadeDTO();
        }

        return mapper.map(afinidade, AfinidadeDTO.class);
    }
}
