package br.com.elotech.oxy.library.domain.usecases.livros;

import br.com.elotech.oxy.library.application.ports.inbound.livros.ListagemLivrosUseCase;
import br.com.elotech.oxy.library.application.ports.outbound.livros.ConsultaLivrosOutboundPort;
import br.com.elotech.oxy.library.domain.models.entities.Livro;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListagemLivrosUseCaseImpl implements ListagemLivrosUseCase {

    private final ConsultaLivrosOutboundPort consultaLivrosOutboundPort;

    public ListagemLivrosUseCaseImpl(ConsultaLivrosOutboundPort consultaLivrosOutboundPort) {
        this.consultaLivrosOutboundPort = consultaLivrosOutboundPort;
    }

    @Override
    public List<Livro> listar() {
        return consultaLivrosOutboundPort.listar();
    }
}
