package br.com.elotech.oxy.library.domain.usecases.usuarios;

import br.com.elotech.oxy.library.application.ports.outbound.usuarios.ConsultaUsuariosOutboundPort;
import br.com.elotech.oxy.library.application.ports.outbound.usuarios.ExclusaoUsuarioOutboundPort;
import br.com.elotech.oxy.library.domain.exception.RecursoNaoEncontradoException;
import br.com.elotech.oxy.library.domain.models.entities.Usuario;
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
class ExclusaoUsuarioUseCaseImplTest {

    @Spy
    @InjectMocks
    private ExclusaoUsuarioUseCaseImpl useCase;

    @Mock
    private ExclusaoUsuarioOutboundPort outboundPort;

    @Mock
    private ConsultaUsuariosOutboundPort consultaUsuariosOutboundPort;

    @Test
    void deveRetornarErroNotFound_aoPassarUsuarioInexistente() {

        RecursoNaoEncontradoException recursoNaoEncontradoException = Assertions.assertThrows(
                RecursoNaoEncontradoException.class,
                () -> useCase.excluir(1)
        );

        Mockito.verify(consultaUsuariosOutboundPort, Mockito.times(1)).consultarPorId(1);

        Assertions.assertTrue(recursoNaoEncontradoException.getMessage().contains("não foi encontrado"));

    }

    @Test
    void deveExcluirLivro_aoPassarLivroExistente() {

        Usuario usuario = new Usuario(1);

        Mockito.when(consultaUsuariosOutboundPort.consultarPorId(1))
                .thenReturn(Optional.of(usuario));

        useCase.excluir(1);

        Mockito.verify(outboundPort, Mockito.times(1)).excluir(usuario);

    }
}
