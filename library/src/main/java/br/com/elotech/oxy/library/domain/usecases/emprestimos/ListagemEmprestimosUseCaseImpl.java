package br.com.elotech.oxy.library.domain.usecases.emprestimos;

import br.com.elotech.oxy.library.application.ports.inbound.emprestimos.ListagemEmprestimosUseCase;
import br.com.elotech.oxy.library.application.ports.outbound.emprestimos.ConsultaEmprestimoOutboundPort;
import br.com.elotech.oxy.library.domain.models.entities.Emprestimo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListagemEmprestimosUseCaseImpl implements ListagemEmprestimosUseCase {

    private final ConsultaEmprestimoOutboundPort consultaEmprestimoOutboundPort;

    public ListagemEmprestimosUseCaseImpl(ConsultaEmprestimoOutboundPort consultaEmprestimoOutboundPort) {
        this.consultaEmprestimoOutboundPort = consultaEmprestimoOutboundPort;
    }

    @Override
    public List<Emprestimo> listar() {
        return consultaEmprestimoOutboundPort.listar();
    }
}
