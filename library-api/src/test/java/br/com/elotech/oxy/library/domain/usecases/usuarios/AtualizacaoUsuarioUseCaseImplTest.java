package br.com.elotech.oxy.library.domain.usecases.usuarios;

import br.com.elotech.oxy.library.application.ports.outbound.usuarios.AtualizacaoUsuarioOutboundPort;
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
class AtualizacaoUsuarioUseCaseImplTest {

    @Spy
    @InjectMocks
    private AtualizacaoUsuarioUseCaseImpl useCase;

    @Mock
    private AtualizacaoUsuarioOutboundPort outboundPort;
    @Mock
    private ConsultaUsuariosOutboundPort consultaUsuariosOutboundPort;


    @Test
    void deveRetornarErroNotFound_aoReceberUsuarioInexistente() {

        Usuario usuario = criarUsuario();

        Mockito.doReturn(Optional.empty()).when(consultaUsuariosOutboundPort).consultarPorId(1);

        RecursoNaoEncontradoException recursoNaoEncontradoException = Assertions.assertThrows(
                RecursoNaoEncontradoException.class,
                () -> useCase.atualizar(1, usuario)
        );

        Assertions.assertTrue(recursoNaoEncontradoException.getMessage().contains("O recurso 'Usuario' com identificador '1' não foi encontrado."));

    }


    @Test
    void deveAtualizar_aoReceberParametrosCorretos() {

        Usuario usuario = criarUsuario();

        Mockito.doReturn(Optional.of(usuario)).when(consultaUsuariosOutboundPort).consultarPorId(1);

        useCase.atualizar(1, usuario);

        Mockito.verify(outboundPort, Mockito.times(1)).atualizar(usuario);

    }


    private static Usuario criarUsuario() {
        return new Usuario(1);
    }
}
