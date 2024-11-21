package br.com.elotech.oxy.library.domain.usecases.livros;

import br.com.elotech.oxy.library.application.ports.outbound.livros.ConsultaLivrosOutboundPort;
import br.com.elotech.oxy.library.application.ports.outbound.usuarios.ConsultaUsuariosOutboundPort;
import br.com.elotech.oxy.library.domain.exception.RecursoNaoEncontradoException;
import br.com.elotech.oxy.library.domain.models.entities.Livro;
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
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class RecomendacaoLivrosUseCaseImplTest {

    @Spy
    @InjectMocks
    private RecomendacaoLivrosUseCaseImpl useCase;

    @Mock
    private ConsultaLivrosOutboundPort consultaLivrosOutboundPort;
    @Mock
    private ConsultaUsuariosOutboundPort consultaUsuariosOutboundPort;


    @Test
    void deveRetornarLista_aoRetornarItens() {

        Usuario usuario = new Usuario(1);

        Mockito.when(consultaUsuariosOutboundPort.consultarPorId(usuario.getId())).thenReturn(Optional.of(usuario));
        Mockito.when(consultaLivrosOutboundPort.listarRecomendacoes(usuario)).thenReturn(List.of(new Livro()));

        List<Livro> livros = useCase.recomendarParaUsuario(usuario.getId());

        Mockito.verify(consultaLivrosOutboundPort, Mockito.times(1)).listarRecomendacoes(usuario);

        Assertions.assertEquals(1, livros.size());

    }

    @Test
    void deveRetornarErroNotFound_aoReceberCUsuarioInexistente() {

        Usuario usuario = new Usuario(1);

        Mockito.when(consultaUsuariosOutboundPort.consultarPorId(usuario.getId())).thenReturn(Optional.empty());

        RecursoNaoEncontradoException recursoNaoEncontradoException = Assertions.assertThrows(
                RecursoNaoEncontradoException.class,
                () -> useCase.recomendarParaUsuario(usuario.getId())
        );

        Assertions.assertTrue(recursoNaoEncontradoException.getMessage().contains("O recurso 'Usuario' com identificador '1' não foi encontrado."));


    }

}
