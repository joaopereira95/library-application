package br.com.elotech.oxy.library.domain.usecases.livros;

import br.com.elotech.oxy.library.application.ports.outbound.livros.ConsultaLivrosOutboundPort;
import br.com.elotech.oxy.library.domain.models.entities.Livro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ListagemLivrosUseCaseImplTest {

    @Spy
    @InjectMocks
    private ListagemLivrosUseCaseImpl useCase;

    @Mock
    private ConsultaLivrosOutboundPort consultaLivrosOutboundPort;

    @Test
    void deveRetornarLista_aoRetornarItens() {

        Mockito.when(consultaLivrosOutboundPort.listar()).thenReturn(List.of(new Livro()));

        List<Livro> livros = useCase.listar();

        Mockito.verify(consultaLivrosOutboundPort, Mockito.times(1)).listar();

        Assertions.assertEquals(1, livros.size());

    }

    @Test
    void deveRetornarListaVazia_aoNaoRetornarItens() {

        Mockito.when(consultaLivrosOutboundPort.listar()).thenReturn(List.of());

        List<Livro> livros = useCase.listar();

        Mockito.verify(consultaLivrosOutboundPort, Mockito.times(1)).listar();

        Assertions.assertTrue(livros.isEmpty());

    }

}
