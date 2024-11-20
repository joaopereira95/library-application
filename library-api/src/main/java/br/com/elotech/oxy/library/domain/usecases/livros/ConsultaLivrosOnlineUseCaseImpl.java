package br.com.elotech.oxy.library.domain.usecases.livros;

import br.com.elotech.oxy.library.application.ports.inbound.livros.ConsultaLivrosOnlineUseCase;
import br.com.elotech.oxy.library.application.ports.outbound.livros.ConsultaLivrosOnlineOutboundPort;
import br.com.elotech.oxy.library.domain.models.LivroOnline;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaLivrosOnlineUseCaseImpl implements ConsultaLivrosOnlineUseCase {

    private final ConsultaLivrosOnlineOutboundPort consultaLivrosOnlineOutboundPort;

    public ConsultaLivrosOnlineUseCaseImpl(ConsultaLivrosOnlineOutboundPort consultaLivrosOnlineOutboundPort) {
        this.consultaLivrosOnlineOutboundPort = consultaLivrosOnlineOutboundPort;
    }

    @Override
    public List<LivroOnline> consultarPorTitulo(String titulo) {
        return consultaLivrosOnlineOutboundPort.consultarPorTitulo(titulo);
    }
}
