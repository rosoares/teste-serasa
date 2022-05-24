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
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ScoreServiceTest {

    @InjectMocks
    private ScoreServiceImpl service;

    @Mock
    private ScoreRepository repository;

    @Mock
    private ModelMapper modelMapper;

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

    @Test
    void testeEncontraPeloScoreDaPessoaComSucesso() {

        Score score = scoreValido();

        Mockito.when(repository.findByScoreInicialLessThanEqualAndScoreFinalGreaterThanEqual(Mockito.anyInt(), Mockito.anyInt())).thenReturn(score);
        Mockito.when(modelMapper.map(Mockito.any(Score.class), Mockito.any())).thenReturn(
                ScoreDTO.builder()
                        .scoreDescricao(score.getScoreDescricao())
                        .scoreFinal(score.getScoreFinal())
                        .scoreInicial(score.getScoreInicial())
                        .build()
        );

        ScoreDTO resposta = service.encontraFaixaPorScore(Mockito.anyInt());

        assertEquals(score.getScoreDescricao(), resposta.getScoreDescricao());
        assertEquals(score.getScoreFinal(), resposta.getScoreFinal());
        assertEquals(score.getScoreInicial(), resposta.getScoreInicial());

    }

    private Score scoreValido() {

        Score score = new Score();
        score.setScoreDescricao("Insuficiente");
        score.setScoreInicial(0);
        score.setScoreFinal(200);

        return score;
    }

}
