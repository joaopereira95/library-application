package br.com.elotech.oxy.library.domain.usecases.livros;

import br.com.elotech.oxy.library.application.ports.outbound.livros.ConsultaLivrosOnlineOutboundPort;
import br.com.elotech.oxy.library.domain.models.LivroOnline;
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
class ConsultaLivrosOnlineUseCaseImplTest {

    @Spy
    @InjectMocks
    private ConsultaLivrosOnlineUseCaseImpl useCase;

    @Mock
    private ConsultaLivrosOnlineOutboundPort consultaLivrosOnlineOutboundPort;

    @Test
    void deveRetornarListaVazia_aoConsultarLivroInexistente() {

        Mockito.when(consultaLivrosOnlineOutboundPort.consultarPorTitulo("Livro Inexistente"))
                .thenReturn(List.of());

        List<LivroOnline> livros = useCase.consultarPorTitulo("Livro Inexistente");

        Assertions.assertTrue(livros.isEmpty());

        Mockito.verify(consultaLivrosOnlineOutboundPort, Mockito.times(1)).consultarPorTitulo("Livro Inexistente");

    }

    @Test
    void deveRetornarLivro_aoConsultarLivroExistente() {

        Mockito.when(consultaLivrosOnlineOutboundPort.consultarPorTitulo("Livro Existente"))
                .thenReturn(List.of(new LivroOnline()));

        List<LivroOnline> livros = useCase.consultarPorTitulo("Livro Existente");

        Mockito.verify(consultaLivrosOnlineOutboundPort, Mockito.times(1)).consultarPorTitulo("Livro Existente");

        Assertions.assertEquals(1, livros.size());

    }
}
