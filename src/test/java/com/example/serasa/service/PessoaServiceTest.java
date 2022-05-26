package com.example.serasa.service;

import com.example.serasa.dto.afinidade.AfinidadeDTO;
import com.example.serasa.dto.pessoa.NovaPessoaDTO;
import com.example.serasa.dto.pessoa.PessoaDTO;
import com.example.serasa.dto.score.ScoreDTO;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {

    @InjectMocks
    private PessoaServiceImpl pessoaService;

    @Mock
    private PessoaRepository repository;

    @Mock
    private ScoreService scoreService;

    @Mock
    private AfinidadeService afinidadeService;

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

    @Test
    void testeEncontraPessoaComSucesso() {

        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(respostaPessoaValida()));
        Mockito.when(scoreService.encontraFaixaPorScore(Mockito.anyInt())).thenReturn(scoreInsuficiente());
        Mockito.when(afinidadeService.encontraPelaRegiao(Mockito.anyString())).thenReturn(afinidadeDTO());
        Mockito.when(mapper.map(Mockito.any(Pessoa.class), Mockito.any())).thenReturn(pessoaDTO1());

        PessoaDTO resposta = pessoaService.encontrar(Mockito.anyLong());

        assertEquals(resposta.getEstados().get(0), "MG");
        assertEquals(resposta.getScoreDescricao(), "Insuficiente");

    }

    @Test
    void testeEncontraTodasAsPessoasComSucesso() {

        Mockito.when(repository.findAll()).thenReturn(List.of(respostaPessoaValida(), respostaPessoaComScoreAceitavel()));
        Mockito.when(afinidadeService.encontraPelaRegiao(Mockito.anyString())).thenReturn(afinidadeDTO());
        Mockito.when(scoreService.encontraFaixaPorScore(Mockito.eq(PessoaTestBuilder.PessoaBuilder.score))).thenReturn(scoreInsuficiente());
        Mockito.when(scoreService.encontraFaixaPorScore(Mockito.eq(600))).thenReturn(scoreAceitavel());
        Mockito.when(mapper.map(Mockito.any(Pessoa.class), Mockito.any())).thenReturn(pessoaDTO1(), pessoaDTO2());

        List<PessoaDTO> resposta = pessoaService.encontrarTodas();

        assertEquals(resposta.get(0).getScoreDescricao(), "Insuficiente");
        assertEquals(resposta.get(1).getScoreDescricao(), "Aceitável");

    }

    private PessoaDTO pessoaDTO1() {
        return PessoaDTO.builder()
                .nome(PessoaTestBuilder.PessoaBuilder.nome)
                .telefone(PessoaTestBuilder.PessoaBuilder.telefone)
                .idade(PessoaTestBuilder.PessoaBuilder.idade)
                .build();
    }

    private PessoaDTO pessoaDTO2() {
        return PessoaDTO.builder()
                .nome("Fulano de tal")
                .telefone("38 99999-9998")
                .idade(45)
                .build();
    }

    private AfinidadeDTO afinidadeDTO() {

        return AfinidadeDTO.builder()
                .estados(List.of("MG", "RJ", "SP", "ES"))
                .regiao("sudeste")
                .build();
    }

    private ScoreDTO scoreInsuficiente() {

        return ScoreDTO.builder()
                .scoreDescricao("Insuficiente")
                .scoreInicial(0)
                .scoreFinal(200)
                .build();
    }

    private ScoreDTO scoreAceitavel() {

        return ScoreDTO.builder()
                .scoreDescricao("Aceitável")
                .scoreInicial(501)
                .scoreFinal(700)
                .build();
    }

    private Pessoa respostaPessoaComScoreAceitavel() {

        Pessoa pessoa = respostaPessoaValida();
        pessoa.setScore(600);

        return pessoa;
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
