package com.example.serasa.service.impl;

import com.example.serasa.dto.afinidade.AfinidadeDTO;
import com.example.serasa.dto.pessoa.NovaPessoaDTO;
import com.example.serasa.dto.pessoa.PessoaDTO;
import com.example.serasa.dto.score.ScoreDTO;
import com.example.serasa.model.Pessoa;
import com.example.serasa.repository.PessoaRepository;
import com.example.serasa.service.AfinidadeService;
import com.example.serasa.service.PessoaService;
import com.example.serasa.service.ScoreService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PessoaServiceImpl implements PessoaService {

    private PessoaRepository repository;
    private ModelMapper mapper;
    private ScoreService scoreService;
    private AfinidadeService afinidadeService;

    public PessoaServiceImpl(PessoaRepository repository, ModelMapper mapper, ScoreService scoreService, AfinidadeService afinidadeService) {
        this.repository = repository;
        this.mapper = mapper;
        this.scoreService = scoreService;
        this.afinidadeService = afinidadeService;
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

    @Override
    public PessoaDTO encontrar(Long id) {

        Optional<Pessoa> pessoa = repository.findById(id);

        if (pessoa.isEmpty()) {
            throw new NotFoundException("Não foi encontrada pessoa com esse ID");
        }

        return mapeiaPessoa(pessoa.get());
    }

    @Override
    public List<PessoaDTO> encontrarTodas() {

        List<Pessoa> pessoas = repository.findAll();

        if (pessoas.isEmpty()) {
            return Collections.emptyList();
        }

        return pessoas.stream()
                .map(this::mapeiaPessoa)
                .collect(Collectors.toList());

    }

    private PessoaDTO mapeiaPessoa(Pessoa pessoa) {

        PessoaDTO pessoaDTO = mapper.map(pessoa, PessoaDTO.class);

        ScoreDTO scoreDTO = scoreService.encontraFaixaPorScore(pessoa.getScore());

        AfinidadeDTO afinidadeDTO = afinidadeService.encontraPelaRegiao(pessoa.getRegiao());

        pessoaDTO.setEstados(afinidadeDTO.getEstados());

        pessoaDTO.setScoreDescricao(scoreDTO.getScoreDescricao());

        return pessoaDTO;

    }
}
