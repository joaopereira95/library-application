package br.com.elotech.oxy.library.domain.usecases.livros;

import br.com.elotech.oxy.library.application.ports.inbound.livros.ImportacaoLivroOnlineUseCase;
import br.com.elotech.oxy.library.application.ports.outbound.categorias.ConsultaCategoriasOutboundPort;
import br.com.elotech.oxy.library.application.ports.outbound.livros.ConsultaLivrosOnlineOutboundPort;
import br.com.elotech.oxy.library.application.ports.outbound.livros.ConsultaLivrosOutboundPort;
import br.com.elotech.oxy.library.application.ports.outbound.livros.CriacaoLivroOutboundPort;
import br.com.elotech.oxy.library.domain.exception.RecursoNaoEncontradoException;
import br.com.elotech.oxy.library.domain.exception.RegraNegocioException;
import br.com.elotech.oxy.library.domain.models.entities.Categoria;
import br.com.elotech.oxy.library.domain.models.entities.Livro;
import br.com.elotech.oxy.library.domain.models.LivroOnline;
import org.springframework.stereotype.Service;

@Service
public class ImportacaoLivroOnlineUseCaseImpl implements ImportacaoLivroOnlineUseCase {

    private final ConsultaLivrosOnlineOutboundPort consultaLivrosOnlineOutboundPort;
    private final ConsultaLivrosOutboundPort consultaLivrosOutboundPort;
    private final ConsultaCategoriasOutboundPort consultaCategoriasOutboundPort;
    private final CriacaoLivroOutboundPort criacaoLivroOutboundPort;

    public ImportacaoLivroOnlineUseCaseImpl(ConsultaLivrosOnlineOutboundPort consultaLivrosOnlineOutboundPort, ConsultaLivrosOutboundPort consultaLivrosOutboundPort, ConsultaCategoriasOutboundPort consultaCategoriasOutboundPort, CriacaoLivroOutboundPort criacaoLivroOutboundPort) {
        this.consultaLivrosOnlineOutboundPort = consultaLivrosOnlineOutboundPort;
        this.consultaLivrosOutboundPort = consultaLivrosOutboundPort;
        this.consultaCategoriasOutboundPort = consultaCategoriasOutboundPort;
        this.criacaoLivroOutboundPort = criacaoLivroOutboundPort;
    }

    @Override
    public Livro importarPorId(String id) {

        LivroOnline livroOnline = consultaLivrosOnlineOutboundPort.consultarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(id, LivroOnline.class));

        if (livroOnline.getIsbn().length() > 13) {
            throw new RegraNegocioException("O livro de identificador %s não possui ISBN cadastrado.".formatted(id));
        }

        consultaLivrosOutboundPort.consultarPorIsbn(livroOnline.getIsbn()).ifPresent(livroExistente -> {
            throw new RegraNegocioException("O livro de identificador %s já está cadastrado com o ISBN %s".formatted(id, livroOnline.getIsbn()));
        });

        Categoria categoria = consultaCategoriasOutboundPort
                .consultarPorNome(livroOnline.getCategoria().getNome())
                .orElseGet(() -> new Categoria(livroOnline.getCategoria().getNome()));

        Livro livro = new Livro(
                livroOnline.getTitulo(),
                livroOnline.getAutor(),
                livroOnline.getIsbn(),
                livroOnline.getDataPublicacao(),
                categoria
        );

        return criacaoLivroOutboundPort.criar(livro);
    }
}
