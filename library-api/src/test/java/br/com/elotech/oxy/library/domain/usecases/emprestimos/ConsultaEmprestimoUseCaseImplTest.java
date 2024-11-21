package br.com.elotech.oxy.library.domain.usecases.emprestimos;

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
class ConsultaEmprestimoUseCaseImplTest {

    @Spy
    @InjectMocks
    private ConsultaEmprestimoUseCaseImpl useCase;

    @Mock
    private ConsultaEmprestimoOutboundPort consultaEmprestimoOutboundPort;

    @Test
    void deveRetornarErroNotFound_aoConsultarEmprestimoInexistente() {

        RecursoNaoEncontradoException recursoNaoEncontradoException = Assertions.assertThrows(
                RecursoNaoEncontradoException.class,
                () -> useCase.consultarPorId(1)
        );

        Mockito.verify(consultaEmprestimoOutboundPort, Mockito.times(1)).consultarPorId(1);

        Assertions.assertTrue(recursoNaoEncontradoException.getMessage().contains("não foi encontrado"));

    }

    @Test
    void deveRetornarEmprestimo_aoConsultarEmprestimoExistente() {

        Mockito.when(consultaEmprestimoOutboundPort.consultarPorId(1))
                .thenReturn(Optional.of(new Emprestimo()));

        Emprestimo emprestimo = useCase.consultarPorId(1);

        Mockito.verify(consultaEmprestimoOutboundPort, Mockito.times(1)).consultarPorId(1);

        Assertions.assertNotNull(emprestimo);

    }
}
