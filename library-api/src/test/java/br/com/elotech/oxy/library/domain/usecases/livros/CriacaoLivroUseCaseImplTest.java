package br.com.elotech.oxy.library.domain.usecases.livros;

import br.com.elotech.oxy.library.application.ports.outbound.categorias.ConsultaCategoriasOutboundPort;
import br.com.elotech.oxy.library.application.ports.outbound.livros.ConsultaLivrosOutboundPort;
import br.com.elotech.oxy.library.application.ports.outbound.livros.CriacaoLivroOutboundPort;
import br.com.elotech.oxy.library.domain.exception.RecursoNaoEncontradoException;
import br.com.elotech.oxy.library.domain.exception.RegraNegocioException;
import br.com.elotech.oxy.library.domain.models.entities.Categoria;
import br.com.elotech.oxy.library.domain.models.entities.Livro;
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
class CriacaoLivroUseCaseImplTest {

    @Spy
    @InjectMocks
    private CriacaoLivroUseCaseImpl useCase;

    @Mock
    private CriacaoLivroOutboundPort outboundPort;
    @Mock
    private ConsultaLivrosOutboundPort consultaLivrosOutboundPort;
    @Mock
    private ConsultaCategoriasOutboundPort consultaCategoriasOutboundPort;


    @Test
    void deveRetornarErroRegraNegocio_aoReceberLivroJaExistente() {

        Livro livro = criarLivro();

        Mockito.doReturn(Optional.of(livro)).when(consultaLivrosOutboundPort).consultarPorIsbn(livro.getIsbn());

        RegraNegocioException exception = Assertions.assertThrows(
                RegraNegocioException.class,
                () -> useCase.criar(livro)
        );

        Assertions.assertTrue(exception.getMessage().contains("O livro de ISBN %s já está cadastrado".formatted(livro.getIsbn())));

    }

    @Test
    void deveRetornarErroNotFound_aoReceberCategoriaInexistente() {

        Livro livro = criarLivro();

        Mockito.doReturn(Optional.empty()).when(consultaCategoriasOutboundPort).consultarPorId(livro.getCategoria().getId());

        RecursoNaoEncontradoException recursoNaoEncontradoException = Assertions.assertThrows(
                RecursoNaoEncontradoException.class,
                () -> useCase.criar(livro)
        );

        Assertions.assertTrue(recursoNaoEncontradoException.getMessage().contains("O recurso 'Categoria' com identificador '1' não foi encontrado."));

    }

    @Test
    void deveCriar_aoReceberParametrosCorretos() {

        Livro livro = criarLivro();

        Mockito.doReturn(Optional.of(livro.getCategoria())).when(consultaCategoriasOutboundPort).consultarPorId(livro.getCategoria().getId());

        useCase.criar(livro);

        Mockito.verify(outboundPort, Mockito.times(1)).criar(livro);

    }


    private static Livro criarLivro() {
        return new Livro("Livro 1", "Autor 1", "ISBN", LocalDate.now(), new Categoria(1, "Categoria 1"));
    }
}
