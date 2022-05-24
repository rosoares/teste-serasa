package com.example.serasa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "AFINIDADE")
public class Afinidade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


}
