package br.com.elotech.oxy.library.domain.usecases.emprestimos;

import br.com.elotech.oxy.library.application.ports.inbound.emprestimos.DevolucaoUseCase;
import br.com.elotech.oxy.library.application.ports.outbound.emprestimos.AtualizacaoEmprestimoOutboundPort;
import br.com.elotech.oxy.library.application.ports.outbound.emprestimos.ConsultaEmprestimoOutboundPort;
import br.com.elotech.oxy.library.domain.enums.StatusEmprestimo;
import br.com.elotech.oxy.library.domain.exception.RecursoNaoEncontradoException;
import br.com.elotech.oxy.library.domain.models.entities.Emprestimo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DevolucaoUseCaseImpl implements DevolucaoUseCase {

    private final AtualizacaoEmprestimoOutboundPort atualizacaoEmprestimoOutboundPort;
    private final ConsultaEmprestimoOutboundPort consultaEmprestimoOutboundPort;

    public DevolucaoUseCaseImpl(AtualizacaoEmprestimoOutboundPort atualizacaoEmprestimoOutboundPort, ConsultaEmprestimoOutboundPort consultaEmprestimoOutboundPort) {
        this.atualizacaoEmprestimoOutboundPort = atualizacaoEmprestimoOutboundPort;
        this.consultaEmprestimoOutboundPort = consultaEmprestimoOutboundPort;
    }

    @Override
    public void devolver(Integer id) {
        Emprestimo emprestimo = consultaEmprestimoOutboundPort.consultarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(id, Emprestimo.class));

        emprestimo.setDataDevolucao(LocalDateTime.now());
        emprestimo.setStatus(StatusEmprestimo.DEVOLVIDO);

        atualizacaoEmprestimoOutboundPort.atualizar(emprestimo);
    }
}
