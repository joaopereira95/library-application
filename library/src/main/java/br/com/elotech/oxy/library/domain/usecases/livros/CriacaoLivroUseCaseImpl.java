package br.com.elotech.oxy.library.domain.usecases.livros;

import br.com.elotech.oxy.library.application.ports.inbound.livros.CriacaoLivroUseCase;
import br.com.elotech.oxy.library.application.ports.outbound.categorias.ConsultaCategoriasOutboundPort;
import br.com.elotech.oxy.library.application.ports.outbound.livros.ConsultaLivrosOutboundPort;
import br.com.elotech.oxy.library.application.ports.outbound.livros.CriacaoLivroOutboundPort;
import br.com.elotech.oxy.library.domain.exception.RecursoNaoEncontradoException;
import br.com.elotech.oxy.library.domain.exception.RegraNegocioException;
import br.com.elotech.oxy.library.domain.models.entities.Categoria;
import br.com.elotech.oxy.library.domain.models.entities.Livro;
import org.springframework.stereotype.Service;

@Service
public class CriacaoLivroUseCaseImpl implements CriacaoLivroUseCase {

    private final CriacaoLivroOutboundPort outboundPort;
    private final ConsultaCategoriasOutboundPort consultaCategoriasOutboundPort;
    private final ConsultaLivrosOutboundPort consultaLivrosOutboundPort;


    public CriacaoLivroUseCaseImpl(CriacaoLivroOutboundPort outboundPort, ConsultaCategoriasOutboundPort consultaCategoriasOutboundPort, ConsultaLivrosOutboundPort consultaLivrosOutboundPort) {
        this.outboundPort = outboundPort;
        this.consultaCategoriasOutboundPort = consultaCategoriasOutboundPort;
        this.consultaLivrosOutboundPort = consultaLivrosOutboundPort;
    }

    @Override
    public Livro criar(Livro novoLivro) {

        consultaLivrosOutboundPort.consultarPorIsbn(novoLivro.getIsbn()).ifPresent(livroExistente -> {
            throw new RegraNegocioException("O livro de ISBN %s já está cadastrado".formatted(livroExistente.getIsbn()));
        });

        Categoria categoria = consultaCategoriasOutboundPort.consultarPorId(novoLivro.getCategoria().getId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(novoLivro.getCategoria().getId(), Categoria.class));

        novoLivro.setCategoria(categoria);

        return outboundPort.criar(novoLivro);
    }

}
