package br.com.elotech.oxy.library.domain.usecases.emprestimos;

import br.com.elotech.oxy.library.application.ports.inbound.emprestimos.ConsultaEmprestimoUseCase;
import br.com.elotech.oxy.library.application.ports.outbound.emprestimos.ConsultaEmprestimoOutboundPort;
import br.com.elotech.oxy.library.domain.exception.RecursoNaoEncontradoException;
import br.com.elotech.oxy.library.domain.models.entities.Emprestimo;
import org.springframework.stereotype.Service;

@Service
public class ConsultaEmprestimoUseCaseImpl implements ConsultaEmprestimoUseCase {

    private final ConsultaEmprestimoOutboundPort consultaEmprestimoOutboundPort;

    public ConsultaEmprestimoUseCaseImpl(ConsultaEmprestimoOutboundPort consultaEmprestimoOutboundPort) {
        this.consultaEmprestimoOutboundPort = consultaEmprestimoOutboundPort;
    }

    @Override
    public Emprestimo consultarPorId(Integer id) {
        return consultaEmprestimoOutboundPort.consultarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(id, Emprestimo.class));
    }
}
