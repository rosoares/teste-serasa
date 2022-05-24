package com.example.serasa.dto.pessoa;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.example.serasa.model.Score.SCORE_MAX;
import static com.example.serasa.model.Score.SCORE_MIN;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NovaPessoaDTO {

    private static final int IDADE_MAX = 140;

    @NotNull(message = "O campo nome não pode estar vazio")
    private String nome;

    @NotNull(message = "O campo telefone não pode estar vazio")
    private String telefone;

    @NotNull(message = "O campo idade não pode estar vazio")
    @Max(value = IDADE_MAX, message = "O campo score não pode ter um valor menor que " + IDADE_MAX)
    private int idade;

    @NotNull(message = "O campo cidade não pode estar vazio")
    private String cidade;

    @NotNull(message = "O campo estado não pode estar vazio")
    private String estado;

    @NotNull(message = "O campo score não pode estar vazio")
    @Min(value = SCORE_MIN, message = "O campo score não pode ter um valor menor que " + SCORE_MIN)
    @Max(value = SCORE_MAX, message = "O campo score não pode ter um valor menor que " + SCORE_MAX)
    private int score;

    @NotNull(message = "O campo regiao não pode estar vazio")
    private String regiao;

}
