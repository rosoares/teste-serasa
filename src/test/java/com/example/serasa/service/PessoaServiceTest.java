package com.example.serasa.service;

import com.example.serasa.dto.pessoa.NovaPessoaDTO;
import com.example.serasa.model.Pessoa;
import com.example.serasa.repository.PessoaRepository;
import com.example.serasa.service.impl.PessoaServiceImpl;
import com.example.serasa.testBuilders.PessoaTestBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {

    @InjectMocks
    private PessoaServiceImpl pessoaService;

    @Mock
    private PessoaRepository repository;

    @Mock
    private ModelMapper mapper;

    @Test
    void testeCriaPessoaComSucesso() {
        Pessoa pessoa = respostaPessoaValida();
        Mockito.when(repository.save(Mockito.any(Pessoa.class))).thenReturn(pessoa);
        Mockito.when(mapper.map(Mockito.any(NovaPessoaDTO.class), Mockito.any())).thenReturn(PessoaTestBuilder.pessoaDefault());

        NovaPessoaDTO requisicao = requisicaoNovaPessoa();

        pessoaService.criar(requisicao);

        ArgumentCaptor<Pessoa> pessoaArgumentCaptor = ArgumentCaptor.forClass(Pessoa.class);
        Mockito.verify(repository).save(pessoaArgumentCaptor.capture());

        assertEquals(requisicao.getNome(), pessoaArgumentCaptor.getValue().getNome());
        assertEquals(requisicao.getTelefone(), pessoaArgumentCaptor.getValue().getTelefone());
        assertEquals(requisicao.getIdade(), pessoaArgumentCaptor.getValue().getIdade());
        assertEquals(requisicao.getCidade(), pessoaArgumentCaptor.getValue().getCidade());
        assertEquals(requisicao.getEstado(), pessoaArgumentCaptor.getValue().getEstado());
        assertEquals(requisicao.getScore(), pessoaArgumentCaptor.getValue().getScore());
        assertEquals(requisicao.getRegiao(), pessoaArgumentCaptor.getValue().getRegiao());
        assertNotNull(pessoaArgumentCaptor.getValue().getId());
        assertNotNull(pessoaArgumentCaptor.getValue().getDataInclusao());
    }

    private Pessoa respostaPessoaValida() {

        Pessoa pessoa = PessoaTestBuilder.pessoaDefault();
        pessoa.setId(PessoaTestBuilder.PessoaBuilder.id);
        pessoa.setDataInclusao(PessoaTestBuilder.PessoaBuilder.dataInclusao);

        return pessoa;
    }

    private NovaPessoaDTO requisicaoNovaPessoa() {

        return NovaPessoaDTO.builder()
                .nome(PessoaTestBuilder.PessoaBuilder.nome)
                .telefone(PessoaTestBuilder.PessoaBuilder.telefone)
                .idade(PessoaTestBuilder.PessoaBuilder.idade)
                .cidade(PessoaTestBuilder.PessoaBuilder.cidade)
                .estado(PessoaTestBuilder.PessoaBuilder.estado)
                .score(PessoaTestBuilder.PessoaBuilder.score)
                .regiao(PessoaTestBuilder.PessoaBuilder.regiao)
                .build();
    }
}
