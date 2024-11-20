package br.com.elotech.oxy.library.domain.usecases.categorias;

import br.com.elotech.oxy.library.application.ports.inbound.categorias.ListagemCategoriasUseCase;
import br.com.elotech.oxy.library.application.ports.outbound.categorias.ConsultaCategoriasOutboundPort;
import br.com.elotech.oxy.library.domain.models.entities.Categoria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListagemCategoriasUseCaseImpl implements ListagemCategoriasUseCase {

    private final ConsultaCategoriasOutboundPort consultaLivrosOutboundPort;

    public ListagemCategoriasUseCaseImpl(ConsultaCategoriasOutboundPort consultaLivrosOutboundPort) {
        this.consultaLivrosOutboundPort = consultaLivrosOutboundPort;
    }

    @Override
    public List<Categoria> listar() {
        return consultaLivrosOutboundPort.listar();
    }
}
