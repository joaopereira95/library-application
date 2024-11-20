package br.com.elotech.oxy.library.infrastructure.adapters.outbound.database.emprestimos;

import br.com.elotech.oxy.library.application.ports.outbound.emprestimos.ConsultaEmprestimoOutboundPort;
import br.com.elotech.oxy.library.domain.enums.StatusEmprestimo;
import br.com.elotech.oxy.library.domain.models.entities.Emprestimo;
import br.com.elotech.oxy.library.domain.models.entities.Livro;
import br.com.elotech.oxy.library.infrastructure.adapters.outbound.database.emprestimos.repository.EmprestimoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ConsultaEmprestimoDatabase implements ConsultaEmprestimoOutboundPort {

    private final EmprestimoRepository repository;

    public ConsultaEmprestimoDatabase(EmprestimoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Emprestimo> consultarPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Emprestimo> listar() {
        return repository.findAllByOrderByIdDesc();
    }

    @Override
    public boolean verificarExistenciaEmprestimo(Livro livro) {
        return repository.existsByLivroAndStatus(livro, StatusEmprestimo.EMPRESTADO);
    }
}
