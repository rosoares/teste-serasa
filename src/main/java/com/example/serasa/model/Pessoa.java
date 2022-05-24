package com.example.serasa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "PESSOA")
@NoArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "TELEFONE", nullable = false)
    private String telefone;

    @Column(name = "IDADE", nullable = false)
    private int idade;

    @Column(name = "CIDADE", nullable = false)
    private String cidade;

    @Column(name = "ESTADO", nullable = false)
    private String estado;

    @Column(name = "SCORE", nullable = false)
    private int score;

    @Column(name = "REGIAO", nullable = false)
    private String regiao;

    @Column(name = "DATA_INCLUSAO")
    private LocalDate dataInclusao;

}
