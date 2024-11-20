package br.com.elotech.oxy.library.domain.usecases.livros;

import br.com.elotech.oxy.library.application.ports.inbound.livros.ConsultaLivroOnlinePorIdUseCase;
import br.com.elotech.oxy.library.application.ports.outbound.livros.ConsultaLivrosOnlineOutboundPort;
import br.com.elotech.oxy.library.domain.exception.RecursoNaoEncontradoException;
import br.com.elotech.oxy.library.domain.models.LivroOnline;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsultaLivroOnlinePorIdUseCaseImpl implements ConsultaLivroOnlinePorIdUseCase {

    private final ConsultaLivrosOnlineOutboundPort consultaLivrosOnlineOutboundPort;

    public ConsultaLivroOnlinePorIdUseCaseImpl(ConsultaLivrosOnlineOutboundPort consultaLivrosOnlineOutboundPort) {
        this.consultaLivrosOnlineOutboundPort = consultaLivrosOnlineOutboundPort;
    }

    @Override
    public LivroOnline consultar(String id) {
        return consultaLivrosOnlineOutboundPort.consultarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(id, LivroOnline.class));
    }
}
