package br.com.elotech.oxy.library.infrastructure.adapters.outbound.database.emprestimos;

import br.com.elotech.oxy.library.application.ports.outbound.emprestimos.CriacaoEmprestimoOutboundPort;
import br.com.elotech.oxy.library.domain.models.entities.Emprestimo;
import br.com.elotech.oxy.library.infrastructure.adapters.outbound.database.emprestimos.repository.EmprestimoRepository;
import org.springframework.stereotype.Component;

@Component
public class CriacaoEmprestimoDatabase implements CriacaoEmprestimoOutboundPort {

    private final EmprestimoRepository repository;

    public CriacaoEmprestimoDatabase(EmprestimoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Emprestimo criar(Emprestimo emprestimo) {
        return repository.save(emprestimo);
    }
}
