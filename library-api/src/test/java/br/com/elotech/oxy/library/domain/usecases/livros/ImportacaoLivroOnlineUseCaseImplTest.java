package br.com.elotech.oxy.library.domain.usecases.livros;

import br.com.elotech.oxy.library.application.ports.outbound.categorias.ConsultaCategoriasOutboundPort;
import br.com.elotech.oxy.library.application.ports.outbound.livros.ConsultaLivrosOnlineOutboundPort;
import br.com.elotech.oxy.library.application.ports.outbound.livros.ConsultaLivrosOutboundPort;
import br.com.elotech.oxy.library.application.ports.outbound.livros.CriacaoLivroOutboundPort;
import br.com.elotech.oxy.library.domain.exception.RecursoNaoEncontradoException;
import br.com.elotech.oxy.library.domain.exception.RegraNegocioException;
import br.com.elotech.oxy.library.domain.models.LivroOnline;
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
class ImportacaoLivroOnlineUseCaseImplTest {

    @Spy
    @InjectMocks
    private ImportacaoLivroOnlineUseCaseImpl useCase;

    @Mock
    private ConsultaLivrosOnlineOutboundPort consultaLivrosOnlineOutboundPort;

    @Mock
    private ConsultaLivrosOutboundPort consultaLivrosOutboundPort;

    @Mock
    private CriacaoLivroOutboundPort criacaoLivroOutboundPort;

    @Mock
    private ConsultaCategoriasOutboundPort consultaCategoriasOutboundPort;


    @Test
    void deveRetornarErroRegraNegocio_aoReceberLivroJaExistente() {

        LivroOnline livroOnline = criarLivroOnline();
        Livro livro = criarLivro();

        Mockito.doReturn(Optional.of(livroOnline)).when(consultaLivrosOnlineOutboundPort).consultarPorId("livro");

        Mockito.doReturn(Optional.of(livro)).when(consultaLivrosOutboundPort).consultarPorIsbn(livro.getIsbn());

        RegraNegocioException exception = Assertions.assertThrows(
                RegraNegocioException.class,
                () -> useCase.importarPorId("livro")
        );

        Assertions.assertTrue(exception.getMessage().contains("O livro de identificador %s já está cadastrado com o ISBN %s".formatted("livro", livro.getIsbn())));

    }

    @Test
    void deveRetornarErro_aoReceberLivroComIsbnInvalido() {

        LivroOnline livroOnline = criarLivroOnline();
        livroOnline.setIsbn("123456789012345");

        Mockito.doReturn(Optional.of(livroOnline)).when(consultaLivrosOnlineOutboundPort).consultarPorId("livro");

        RegraNegocioException exception = Assertions.assertThrows(
                RegraNegocioException.class,
                () -> useCase.importarPorId("livro")
        );

        Assertions.assertTrue(exception.getMessage().contains("O livro de identificador livro não possui ISBN cadastrado."));

    }

    @Test
    void deveCriar_aoReceberParametrosCorretos() {

        LivroOnline livroOnline = criarLivroOnline();
        Livro livro = criarLivro();

        Mockito.doReturn(Optional.of(livroOnline)).when(consultaLivrosOnlineOutboundPort).consultarPorId("livro");

        Mockito.doReturn(Optional.empty()).when(consultaLivrosOutboundPort).consultarPorIsbn(livro.getIsbn());

        Mockito.doReturn(Optional.of(livro.getCategoria())).when(consultaCategoriasOutboundPort).consultarPorNome(livro.getCategoria().getNome());

        useCase.importarPorId("livro");

        Mockito.verify(criacaoLivroOutboundPort, Mockito.times(1)).criar(Mockito.any());

    }


    private static LivroOnline criarLivroOnline() {
        return new LivroOnline("livro", "Livro 1", "Autor 1", "ISBN", LocalDate.now(), new Categoria(1, "Categoria 1"));
    }

    private static Livro criarLivro() {
        return new Livro("Livro 1", "Autor 1", "ISBN", LocalDate.now(), new Categoria(1, "Categoria 1"));
    }
}
