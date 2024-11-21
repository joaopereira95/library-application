package br.com.elotech.oxy.library.domain.usecases.livros;

import br.com.elotech.oxy.library.application.ports.outbound.livros.ConsultaLivrosOutboundPort;
import br.com.elotech.oxy.library.application.ports.outbound.livros.ExclusaoLivroOutboundPort;
import br.com.elotech.oxy.library.domain.exception.RecursoNaoEncontradoException;
import br.com.elotech.oxy.library.domain.models.entities.Livro;
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
class ExclusaoLivroUseCaseImplTest {

    @Spy
    @InjectMocks
    private ExclusaoLivroUseCaseImpl useCase;

    @Mock
    private ExclusaoLivroOutboundPort exclusaoLivroOutboundPort;

    @Mock
    private ConsultaLivrosOutboundPort consultaLivrosOutboundPort;

    @Test
    void deveRetornarErroNotFound_aoPassarLivroInexistente() {

        RecursoNaoEncontradoException recursoNaoEncontradoException = Assertions.assertThrows(
                RecursoNaoEncontradoException.class,
                () -> useCase.excluir(1)
        );

        Mockito.verify(consultaLivrosOutboundPort, Mockito.times(1)).consultarPorId(1);

        Assertions.assertTrue(recursoNaoEncontradoException.getMessage().contains("não foi encontrado"));

    }

    @Test
    void deveExcluirLivro_aoPassarLivroExistente() {

        Livro livro = new Livro();

        Mockito.when(consultaLivrosOutboundPort.consultarPorId(1))
                .thenReturn(Optional.of(livro));

        useCase.excluir(1);

        Mockito.verify(exclusaoLivroOutboundPort, Mockito.times(1)).excluir(livro);

    }
}
