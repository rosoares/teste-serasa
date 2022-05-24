package com.example.serasa.dto.pessoa;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PessoaDTO {

    private Long id;

    private String nome;

    private String telefone;

    private int idade;

    private int scoreDescricao;

    private List<String> estados;

}
