package com.example.serasa.service;

import com.example.serasa.dto.afinidade.AfinidadeDTO;

public interface AfinidadeService {

    void criar(AfinidadeDTO afinidadeDTO);

    AfinidadeDTO encontraPelaRegiao(String regiao);
}
