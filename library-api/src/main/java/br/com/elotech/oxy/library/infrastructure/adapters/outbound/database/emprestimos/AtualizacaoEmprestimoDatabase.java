package br.com.elotech.oxy.library.infrastructure.adapters.outbound.database.emprestimos;

import br.com.elotech.oxy.library.application.ports.outbound.emprestimos.AtualizacaoEmprestimoOutboundPort;
import br.com.elotech.oxy.library.domain.models.entities.Emprestimo;
import br.com.elotech.oxy.library.infrastructure.adapters.outbound.database.emprestimos.repository.EmprestimoRepository;
import org.springframework.stereotype.Component;

@Component
public class AtualizacaoEmprestimoDatabase implements AtualizacaoEmprestimoOutboundPort {

    private final EmprestimoRepository repository;

    public AtualizacaoEmprestimoDatabase(EmprestimoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void atualizar(Emprestimo emprestimo) {
        repository.save(emprestimo);
    }
}
