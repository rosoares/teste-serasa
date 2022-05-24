package com.example.serasa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "SCORE")
@NoArgsConstructor
public class Score {

    public static final int SCORE_MAX = 1000;

    public static final int SCORE_MIN = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "SCORE_DESCRICAO", nullable = false)
    private String scoreDescricao;

    @Column(name = "INICIAL", nullable = false)
    private int scoreInicial;

    @Column(name = "FINAL", nullable = false)
    private int scoreFinal;

}
