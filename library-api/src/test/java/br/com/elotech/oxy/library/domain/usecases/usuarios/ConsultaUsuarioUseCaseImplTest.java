package br.com.elotech.oxy.library.domain.usecases.usuarios;

import br.com.elotech.oxy.library.application.ports.outbound.usuarios.ConsultaUsuariosOutboundPort;
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
class ConsultaUsuarioUseCaseImplTest {

    @Spy
    @InjectMocks
    private ConsultaUsuarioUseCaseImpl useCase;

    @Mock
    private ConsultaUsuariosOutboundPort consultaUsuariosOutboundPortO;

    @Test
    void deveRetornarErroNotFound_aoConsultarUsuarioInexistente() {

        RecursoNaoEncontradoException recursoNaoEncontradoException = Assertions.assertThrows(
                RecursoNaoEncontradoException.class,
                () -> useCase.consultarPorId(1)
        );

        Mockito.verify(consultaUsuariosOutboundPortO, Mockito.times(1)).consultarPorId(1);

        Assertions.assertTrue(recursoNaoEncontradoException.getMessage().contains("não foi encontrado"));

    }

    @Test
    void deveRetornarUsuario_aoConsultarUsuarioExistente() {

        Mockito.when(consultaUsuariosOutboundPortO.consultarPorId(1))
                .thenReturn(Optional.of(new Usuario(1)));

        Usuario usuario = useCase.consultarPorId(1);

        Mockito.verify(consultaUsuariosOutboundPortO, Mockito.times(1)).consultarPorId(1);

        Assertions.assertNotNull(usuario);

    }
}
