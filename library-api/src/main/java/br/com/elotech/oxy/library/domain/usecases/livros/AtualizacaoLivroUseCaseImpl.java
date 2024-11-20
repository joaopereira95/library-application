package br.com.elotech.oxy.library.domain.usecases.livros;

import br.com.elotech.oxy.library.application.ports.inbound.livros.AtualizacaoLivroUseCase;
import br.com.elotech.oxy.library.application.ports.outbound.categorias.ConsultaCategoriasOutboundPort;
import br.com.elotech.oxy.library.application.ports.outbound.livros.AtualizacaoLivroOutboundPort;
import br.com.elotech.oxy.library.application.ports.outbound.livros.ConsultaLivrosOutboundPort;
import br.com.elotech.oxy.library.domain.exception.RecursoNaoEncontradoException;
import br.com.elotech.oxy.library.domain.models.entities.Categoria;
import br.com.elotech.oxy.library.domain.models.entities.Livro;
import org.springframework.stereotype.Service;

@Service
public class AtualizacaoLivroUseCaseImpl implements AtualizacaoLivroUseCase {

    private final AtualizacaoLivroOutboundPort outboundPort;
    private final ConsultaLivrosOutboundPort consultaLivrosOutboundPort;
    private final ConsultaCategoriasOutboundPort consultaCategoriasOutboundPort;

    public AtualizacaoLivroUseCaseImpl(AtualizacaoLivroOutboundPort outboundPort, ConsultaLivrosOutboundPort consultaLivrosOutboundPort, ConsultaCategoriasOutboundPort consultaCategoriasOutboundPort) {
        this.outboundPort = outboundPort;
        this.consultaLivrosOutboundPort = consultaLivrosOutboundPort;
        this.consultaCategoriasOutboundPort = consultaCategoriasOutboundPort;
    }

    @Override
    public void atualizar(Integer id, Livro novoLivro) {

        Livro livroExistente = consultaLivrosOutboundPort.consultarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(novoLivro.getId(), Livro.class));

        Categoria categoria = consultaCategoriasOutboundPort.consultarPorId(novoLivro.getCategoria().getId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(novoLivro.getCategoria().getId(), Categoria.class));

        livroExistente.setAutor(novoLivro.getAutor());
        livroExistente.setTitulo(novoLivro.getTitulo());
        livroExistente.setCategoria(categoria);
        livroExistente.setIsbn(novoLivro.getIsbn());
        livroExistente.setDataPublicacao(novoLivro.getDataPublicacao());

        outboundPort.atualizar(livroExistente);
    }
}
