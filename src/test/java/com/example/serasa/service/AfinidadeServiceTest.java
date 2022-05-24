package com.example.serasa.service;

import com.example.serasa.dto.afinidade.AfinidadeDTO;
import com.example.serasa.model.Afinidade;
import com.example.serasa.repository.AfinidadeRepository;
import com.example.serasa.service.impl.AfinidadeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AfinidadeServiceTest {

    @InjectMocks
    private AfinidadeServiceImpl service;

    @Mock
    private AfinidadeRepository repository;

    @Test
    void testeCriaAfinidadeComSucesso() {

        AfinidadeDTO afinidadeDTO = AfinidadeDTO.builder()
                .regiao("sudeste")
                .estados(Stream.of("MG", "SP", "RJ", "ES").collect(Collectors.toList()))
                .build();

        service.criar(afinidadeDTO);

        ArgumentCaptor<Afinidade> afinidadeArgumentCaptor = ArgumentCaptor.forClass(Afinidade.class);
        Mockito.verify(repository).save(afinidadeArgumentCaptor.capture());

        assertEquals(afinidadeDTO.getRegiao(), afinidadeArgumentCaptor.getValue().getRegiao());
        assertEquals(afinidadeDTO.getEstados().get(0), afinidadeArgumentCaptor.getValue().getEstados().get(0));
        assertEquals(afinidadeDTO.getEstados().get(3), afinidadeArgumentCaptor.getValue().getEstados().get(3));

    }

}
