package br.com.elotech.oxy.library.domain.usecases.emprestimos;

import br.com.elotech.oxy.library.application.ports.outbound.emprestimos.AtualizacaoEmprestimoOutboundPort;
import br.com.elotech.oxy.library.application.ports.outbound.emprestimos.ConsultaEmprestimoOutboundPort;
import br.com.elotech.oxy.library.domain.exception.RecursoNaoEncontradoException;
import br.com.elotech.oxy.library.domain.models.entities.Emprestimo;
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
class DevolucaoUseCaseImplTest {

    @Spy
    @InjectMocks
    private DevolucaoUseCaseImpl useCase;

    @Mock
    private AtualizacaoEmprestimoOutboundPort atualizacaoEmprestimoOutboundPort;

    @Mock
    private ConsultaEmprestimoOutboundPort consultaEmprestimoOutboundPort;

    @Test
    void deveRetornarErroNotFound_aoReceberEmprestimoInexistente() {

        RecursoNaoEncontradoException recursoNaoEncontradoException = Assertions.assertThrows(
                RecursoNaoEncontradoException.class,
                () -> useCase.devolver(1)
        );

        Assertions.assertTrue(recursoNaoEncontradoException.getMessage().equals("O recurso 'Emprestimo' com identificador '1' não foi encontrado."));

    }

    @Test
    void deveDevolverComSucesso_aoDevolverEmprestimoExistente() {

        Mockito.when(consultaEmprestimoOutboundPort.consultarPorId(1))
                .thenReturn(Optional.of(new Emprestimo()));

        useCase.devolver(1);

        Mockito.verify(consultaEmprestimoOutboundPort, Mockito.times(1)).consultarPorId(1);

    }
}
