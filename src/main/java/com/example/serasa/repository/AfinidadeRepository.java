package com.example.serasa.repository;

import com.example.serasa.model.Afinidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AfinidadeRepository extends JpaRepository<Afinidade, Long> {

    Afinidade findByRegiao(String regiao);

}
