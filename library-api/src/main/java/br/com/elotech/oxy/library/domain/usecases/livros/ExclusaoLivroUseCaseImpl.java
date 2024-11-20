package br.com.elotech.oxy.library.domain.usecases.livros;

import br.com.elotech.oxy.library.application.ports.inbound.livros.ExclusaoLivroUseCase;
import br.com.elotech.oxy.library.application.ports.outbound.livros.ConsultaLivrosOutboundPort;
import br.com.elotech.oxy.library.application.ports.outbound.livros.ExclusaoLivroOutboundPort;
import br.com.elotech.oxy.library.domain.exception.RecursoNaoEncontradoException;
import br.com.elotech.oxy.library.domain.models.entities.Livro;
import org.springframework.stereotype.Service;

@Service
public class ExclusaoLivroUseCaseImpl implements ExclusaoLivroUseCase {

    private final ExclusaoLivroOutboundPort outboundPort;
    private final ConsultaLivrosOutboundPort consultaLivrosOutboundPort;

    public ExclusaoLivroUseCaseImpl(ExclusaoLivroOutboundPort outboundPort, ConsultaLivrosOutboundPort consultaLivrosOutboundPort) {
        this.outboundPort = outboundPort;
        this.consultaLivrosOutboundPort = consultaLivrosOutboundPort;
    }

    @Override
    public void excluir(Integer id) {
        Livro livro = consultaLivrosOutboundPort.consultarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(id, Livro.class));

        outboundPort.excluir(livro);
    }
}
