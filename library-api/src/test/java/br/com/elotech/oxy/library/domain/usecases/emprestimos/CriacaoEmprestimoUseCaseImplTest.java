package br.com.elotech.oxy.library.domain.usecases.emprestimos;

import br.com.elotech.oxy.library.application.ports.outbound.emprestimos.ConsultaEmprestimoOutboundPort;
import br.com.elotech.oxy.library.application.ports.outbound.emprestimos.CriacaoEmprestimoOutboundPort;
import br.com.elotech.oxy.library.application.ports.outbound.livros.ConsultaLivrosOutboundPort;
import br.com.elotech.oxy.library.application.ports.outbound.usuarios.ConsultaUsuariosOutboundPort;
import br.com.elotech.oxy.library.domain.exception.RecursoNaoEncontradoException;
import br.com.elotech.oxy.library.domain.exception.RegraNegocioException;
import br.com.elotech.oxy.library.domain.models.entities.Emprestimo;
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

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CriacaoEmprestimoUseCaseImplTest {

    @Spy
    @InjectMocks
    private CriacaoEmprestimoUseCaseImpl useCase;

    @Mock
    private CriacaoEmprestimoOutboundPort criacaoEmprestimoOutboundPort;
    @Mock
    private ConsultaEmprestimoOutboundPort consultaEmprestimoOutboundPort;
    @Mock
    private ConsultaUsuariosOutboundPort consultaUsuariosOutboundPort;
    @Mock
    private ConsultaLivrosOutboundPort consultaLivrosOutboundPort;

    @Test
    void deveRetornarErroNotFound_aoReceberUsuarioInexistente() {

        Emprestimo emprestimo = criarEmprestimo();

        Mockito.doReturn(Optional.empty()).when(consultaUsuariosOutboundPort).consultarPorId(emprestimo.getUsuario().getId());

        RecursoNaoEncontradoException recursoNaoEncontradoException = Assertions.assertThrows(
                RecursoNaoEncontradoException.class,
                () -> useCase.criar(emprestimo)
        );

        Assertions.assertTrue(recursoNaoEncontradoException.getMessage().contains("O recurso 'Usuario' com identificador '1' não foi encontrado."));

    }

    @Test
    void deveRetornarErroNotFound_aoReceberLivroInexistente() {

        Emprestimo emprestimo = criarEmprestimo();

        Mockito.doReturn(Optional.of(emprestimo.getUsuario())).when(consultaUsuariosOutboundPort).consultarPorId(emprestimo.getUsuario().getId());
        Mockito.doReturn(Optional.empty()).when(consultaLivrosOutboundPort).consultarPorId(emprestimo.getLivro().getId());

        RecursoNaoEncontradoException recursoNaoEncontradoException = Assertions.assertThrows(
                RecursoNaoEncontradoException.class,
                () -> useCase.criar(emprestimo)
        );

        Assertions.assertTrue(recursoNaoEncontradoException.getMessage().contains("O recurso 'Livro' com identificador '1' não foi encontrado."));

    }

    @Test
    void deveRetornarErroRegraNegocio_aoReceberLivroJaEmprestado() {

        Emprestimo emprestimo = criarEmprestimo();

        Mockito.doReturn(Optional.of(emprestimo.getUsuario())).when(consultaUsuariosOutboundPort).consultarPorId(emprestimo.getUsuario().getId());
        Mockito.doReturn(Optional.of(emprestimo.getLivro())).when(consultaLivrosOutboundPort).consultarPorId(emprestimo.getLivro().getId());

        Mockito.when(consultaEmprestimoOutboundPort.verificarExistenciaEmprestimo(emprestimo.getLivro()))
                .thenReturn(true);

        RegraNegocioException exception = Assertions.assertThrows(
                RegraNegocioException.class,
                () -> useCase.criar(emprestimo)
        );

        Assertions.assertTrue(exception.getMessage().contains("Livro 'Livro Teste' indisponível no momento. Tente novamente outro dia."));

    }

    @Test
    void deveCriarEmprestimo_aoReceberDadosCorretos() {

        Emprestimo emprestimo = criarEmprestimo();

        Mockito.doReturn(Optional.of(emprestimo.getUsuario())).when(consultaUsuariosOutboundPort).consultarPorId(emprestimo.getUsuario().getId());
        Mockito.doReturn(Optional.of(emprestimo.getLivro())).when(consultaLivrosOutboundPort).consultarPorId(emprestimo.getLivro().getId());

        useCase.criar(emprestimo);

        Mockito.verify(criacaoEmprestimoOutboundPort, Mockito.times(1)).criar(emprestimo);

    }

    private static Emprestimo criarEmprestimo() {

        Usuario usuario = new Usuario();
        usuario.setId(1);

        Livro livro = new Livro();
        livro.setId(1);
        livro.setTitulo("Livro Teste");

        LocalDateTime dataEmprestimo = LocalDateTime.now();

        return new Emprestimo(usuario, livro, dataEmprestimo);
    }
}
