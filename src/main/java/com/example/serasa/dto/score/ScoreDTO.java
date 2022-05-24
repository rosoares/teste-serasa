package com.example.serasa.dto.score;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static com.example.serasa.model.Score.SCORE_MAX;
import static com.example.serasa.model.Score.SCORE_MIN;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreDTO {

    @NotNull(message = "O campo scoreDescricao não pode estar vazio")
    private String scoreDescricao;

    @NotNull(message = "O campo inicial não pode estar vazio")
    @Min(value = SCORE_MIN, message = "O campo inicial não pode ter um valor menor que " + SCORE_MIN)
    @Max(value = SCORE_MAX, message = "O campo inicial não pode ter um valor menor que " + SCORE_MAX)
    @JsonProperty("inicial")
    private int scoreInicial;

    @NotNull(message = "O campo final não pode estar vazio")
    @Min(value = SCORE_MIN, message = "O campo final não pode ter um valor menor que " + SCORE_MIN)
    @Max(value = SCORE_MAX, message = "O campo final não pode ter um valor menor que " + SCORE_MAX)
    @JsonProperty("final")
    private int scoreFinal;

}
