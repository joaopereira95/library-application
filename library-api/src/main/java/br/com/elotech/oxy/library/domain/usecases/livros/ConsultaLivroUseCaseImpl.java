package br.com.elotech.oxy.library.domain.usecases.livros;

import br.com.elotech.oxy.library.application.ports.inbound.livros.ConsultaLivroUseCase;
import br.com.elotech.oxy.library.application.ports.outbound.livros.ConsultaLivrosOutboundPort;
import br.com.elotech.oxy.library.domain.exception.RecursoNaoEncontradoException;
import br.com.elotech.oxy.library.domain.models.entities.Livro;
import org.springframework.stereotype.Service;

@Service
public class ConsultaLivroUseCaseImpl implements ConsultaLivroUseCase {

    private final ConsultaLivrosOutboundPort consultaLivrosOutboundPort;

    public ConsultaLivroUseCaseImpl(ConsultaLivrosOutboundPort consultaLivrosOutboundPort) {
        this.consultaLivrosOutboundPort = consultaLivrosOutboundPort;
    }

    @Override
    public Livro consultarPorId(Integer id) {
        return consultaLivrosOutboundPort.consultarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(id, Livro.class));
    }

}
