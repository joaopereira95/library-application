package br.com.elotech.oxy.library.domain.usecases.livros;

import br.com.elotech.oxy.library.application.ports.outbound.categorias.ConsultaCategoriasOutboundPort;
import br.com.elotech.oxy.library.application.ports.outbound.emprestimos.AtualizacaoEmprestimoOutboundPort;
import br.com.elotech.oxy.library.application.ports.outbound.emprestimos.ConsultaEmprestimoOutboundPort;
import br.com.elotech.oxy.library.application.ports.outbound.livros.AtualizacaoLivroOutboundPort;
import br.com.elotech.oxy.library.application.ports.outbound.livros.ConsultaLivrosOutboundPort;
import br.com.elotech.oxy.library.domain.exception.RecursoNaoEncontradoException;
import br.com.elotech.oxy.library.domain.models.entities.Categoria;
import br.com.elotech.oxy.library.domain.models.entities.Emprestimo;
import br.com.elotech.oxy.library.domain.models.entities.Livro;
import br.com.elotech.oxy.library.domain.usecases.emprestimos.DevolucaoUseCaseImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AtualizacaoLivroUseCaseImplTest {

    @Spy
    @InjectMocks
    private AtualizacaoLivroUseCaseImpl useCase;

    @Mock
    private AtualizacaoLivroOutboundPort outboundPort;
    @Mock
    private ConsultaLivrosOutboundPort consultaLivrosOutboundPort;
    @Mock
    private ConsultaCategoriasOutboundPort consultaCategoriasOutboundPort;


    @Test
    void deveRetornarErroNotFound_aoReceberLivroInexistente() {

        Livro livro = criarLivro();

        Mockito.doReturn(Optional.empty()).when(consultaLivrosOutboundPort).consultarPorId(1);

        RecursoNaoEncontradoException recursoNaoEncontradoException = Assertions.assertThrows(
                RecursoNaoEncontradoException.class,
                () -> useCase.atualizar(1, livro)
        );

        Assertions.assertTrue(recursoNaoEncontradoException.getMessage().contains("O recurso 'Livro' com identificador '1' não foi encontrado."));

    }

    @Test
    void deveRetornarErroNotFound_aoReceberCategoriaInexistente() {

        Livro livro = criarLivro();

        Mockito.doReturn(Optional.of(livro)).when(consultaLivrosOutboundPort).consultarPorId(1);
        Mockito.doReturn(Optional.empty()).when(consultaCategoriasOutboundPort).consultarPorId(livro.getCategoria().getId());

        RecursoNaoEncontradoException recursoNaoEncontradoException = Assertions.assertThrows(
                RecursoNaoEncontradoException.class,
                () -> useCase.atualizar(1, livro)
        );

        Assertions.assertTrue(recursoNaoEncontradoException.getMessage().contains("O recurso 'Categoria' com identificador '1' não foi encontrado."));

    }

    @Test
    void deveAtualizar_aoReceberParametrosCorretos() {

        Livro livro = criarLivro();

        Mockito.doReturn(Optional.of(livro)).when(consultaLivrosOutboundPort).consultarPorId(1);
        Mockito.doReturn(Optional.of(livro.getCategoria())).when(consultaCategoriasOutboundPort).consultarPorId(livro.getCategoria().getId());

        useCase.atualizar(1, livro);

        Mockito.verify(outboundPort, Mockito.times(1)).atualizar(livro);

    }


    private static Livro criarLivro() {
        return new Livro("Livro 1", "Autor 1", "ISBN", LocalDate.now(), new Categoria(1, "Categoria 1"));
    }
}
