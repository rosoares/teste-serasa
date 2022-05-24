package com.example.serasa.testBuilders;

import com.example.serasa.model.Pessoa;
import lombok.Builder;

import java.time.LocalDate;

public class PessoaTestBuilder {

    @Builder
    public static Pessoa pessoa(Long id, String nome, String telefone, int idade, String cidade, String estado, int score, String regiao, LocalDate dataInclusao) {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        pessoa.setNome(nome);
        pessoa.setTelefone(telefone);
        pessoa.setIdade(idade);
        pessoa.setCidade(cidade);
        pessoa.setEstado(estado);
        pessoa.setScore(score);
        pessoa.setRegiao(regiao);
        pessoa.setDataInclusao(dataInclusao);

        return pessoa;
    }

    public static Pessoa pessoaDefault() {
        return builder()
                .cidade(PessoaBuilder.cidade)
                .estado(PessoaBuilder.estado)
                .idade(PessoaBuilder.idade)
                .telefone(PessoaBuilder.telefone)
                .nome(PessoaBuilder.nome)
                .score(PessoaBuilder.score)
                .regiao(PessoaBuilder.regiao)
                .build();

    }

    public static class PessoaBuilder {
        public static long id = 1L;
        public static String nome = "Nome";
        public static String telefone = "(99) 99999-9999";
        public static int idade = 25;
        public static String cidade = "Cidade";
        public static String estado = "MG";
        public static LocalDate dataInclusao = LocalDate.now();
        public static String regiao = "sudeste";
        public static int score = 200;
    }
}
