package com.example.serasa.model;

import com.example.serasa.model.converter.ListToStringConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "AFINIDADE")
public class Afinidade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "REGIAO", nullable = false)
    private String regiao;

    @Column(name = "ESTADOS", nullable = false)
    @Convert(converter = ListToStringConverter.class)
    private List<String> estados;

}
