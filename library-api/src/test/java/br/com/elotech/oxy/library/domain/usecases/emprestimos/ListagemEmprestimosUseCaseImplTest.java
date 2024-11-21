package br.com.elotech.oxy.library.domain.usecases.emprestimos;

import br.com.elotech.oxy.library.application.ports.outbound.emprestimos.ConsultaEmprestimoOutboundPort;
import br.com.elotech.oxy.library.domain.models.entities.Emprestimo;
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
class ListagemEmprestimosUseCaseImplTest {

    @Spy
    @InjectMocks
    private ListagemEmprestimosUseCaseImpl useCase;

    @Mock
    private ConsultaEmprestimoOutboundPort consultaEmprestimoOutboundPort;

    @Test
    void deveRetornarLista_aoRetornarItens() {

        Mockito.when(consultaEmprestimoOutboundPort.listar()).thenReturn(List.of(new Emprestimo()));

        List<Emprestimo> emprestimos = useCase.listar();

        Mockito.verify(consultaEmprestimoOutboundPort, Mockito.times(1)).listar();

        Assertions.assertEquals(1, emprestimos.size());

    }

    @Test
    void deveRetornarListaVazia_aoNaoRetornarItens() {

        Mockito.when(consultaEmprestimoOutboundPort.listar()).thenReturn(List.of());

        List<Emprestimo> emprestimos = useCase.listar();

        Mockito.verify(consultaEmprestimoOutboundPort, Mockito.times(1)).listar();

        Assertions.assertTrue(emprestimos.isEmpty());

    }

}
