package com.example.serasa.repository;

import com.example.serasa.dto.score.ScoreDTO;
import com.example.serasa.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, Long> {

    Score findByInicialGreaterThanAndFinalLessThanEqual(int score);

}
