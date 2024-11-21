package br.com.elotech.oxy.library.domain.usecases.usuarios;

import br.com.elotech.oxy.library.application.ports.outbound.usuarios.ConsultaUsuariosOutboundPort;
import br.com.elotech.oxy.library.domain.models.entities.Usuario;
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
class ListagemUsuariosUseCaseImplTest {

    @Spy
    @InjectMocks
    private ListagemUsuariosUseCaseImpl useCase;

    @Mock
    private ConsultaUsuariosOutboundPort outboundPort;

    @Test
    void deveRetornarLista_aoRetornarItens() {

        Mockito.when(outboundPort.listar()).thenReturn(List.of(new Usuario()));

        List<Usuario> usuarios = useCase.listar();

        Mockito.verify(outboundPort, Mockito.times(1)).listar();

        Assertions.assertEquals(1, usuarios.size());

    }

    @Test
    void deveRetornarListaVazia_aoNaoRetornarItens() {

        Mockito.when(outboundPort.listar()).thenReturn(List.of());

        List<Usuario> usuarios = useCase.listar();

        Mockito.verify(outboundPort, Mockito.times(1)).listar();

        Assertions.assertTrue(usuarios.isEmpty());

    }

}
