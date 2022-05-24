package com.example.serasa.service;

import com.example.serasa.dto.score.ScoreDTO;
import com.example.serasa.model.Score;
import com.example.serasa.repository.ScoreRepository;
import com.example.serasa.service.impl.ScoreServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ScoreServiceTest {

    @InjectMocks
    private ScoreServiceImpl service;

    @Mock
    private ScoreRepository repository;

    @Test
    void testeCriaScoreComSucesso() {

        ScoreDTO scoreDTO = ScoreDTO.builder()
                .scoreDescricao("Insuficiente")
                .scoreInicial(0)
                .scoreFinal(200)
                .build();

        service.criar(scoreDTO);

        ArgumentCaptor<Score> scoreArgumentCaptor = ArgumentCaptor.forClass(Score.class);
        Mockito.verify(repository).save(scoreArgumentCaptor.capture());

        assertEquals(scoreDTO.getScoreDescricao(), scoreArgumentCaptor.getValue().getScoreDescricao());
        assertEquals(scoreDTO.getScoreFinal(), scoreArgumentCaptor.getValue().getScoreFinal());
        assertEquals(scoreDTO.getScoreInicial(), scoreArgumentCaptor.getValue().getScoreInicial());

    }

}
