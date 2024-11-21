package br.com.elotech.oxy.library.domain.usecases.livros;

import br.com.elotech.oxy.library.application.ports.outbound.livros.ConsultaLivrosOnlineOutboundPort;
import br.com.elotech.oxy.library.domain.exception.RecursoNaoEncontradoException;
import br.com.elotech.oxy.library.domain.models.LivroOnline;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ConsultaLivroOnlinePorIdUseCaseImplTest {

    @Spy
    @InjectMocks
    private ConsultaLivroOnlinePorIdUseCaseImpl useCase;

    @Mock
    private ConsultaLivrosOnlineOutboundPort consultaLivrosOnlineOutboundPort;

    @Test
    void deveRetornarErroNotFound_aoConsultarLivroInexistente() {

        RecursoNaoEncontradoException recursoNaoEncontradoException = Assertions.assertThrows(
                RecursoNaoEncontradoException.class,
                () -> useCase.consultar("1")
        );

        Mockito.verify(consultaLivrosOnlineOutboundPort, Mockito.times(1)).consultarPorId("1");

        Assertions.assertTrue(recursoNaoEncontradoException.getMessage().contains("não foi encontrado"));

    }

    @Test
    void deveRetornarLivro_aoConsultarLivroExistente() {

        Mockito.when(consultaLivrosOnlineOutboundPort.consultarPorId("1"))
                .thenReturn(Optional.of(new LivroOnline()));

        LivroOnline livro = useCase.consultar("1");

        Mockito.verify(consultaLivrosOnlineOutboundPort, Mockito.times(1)).consultarPorId("1");

        Assertions.assertNotNull(livro);

    }
}
