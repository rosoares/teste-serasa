package com.example.serasa.service.impl;

import com.example.serasa.dto.score.ScoreDTO;
import com.example.serasa.model.Score;
import com.example.serasa.repository.ScoreRepository;
import com.example.serasa.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreServiceImpl implements ScoreService {

    private ScoreRepository repository;

    @Autowired
    public ScoreServiceImpl(ScoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public void criar(ScoreDTO scoreDTO) {

        Score score = new Score();
        score.setScoreDescricao(scoreDTO.getScoreDescricao());
        score.setScoreInicial(scoreDTO.getScoreInicial());
        score.setScoreFinal(scoreDTO.getScoreFinal());

        repository.save(score);

    }

}
