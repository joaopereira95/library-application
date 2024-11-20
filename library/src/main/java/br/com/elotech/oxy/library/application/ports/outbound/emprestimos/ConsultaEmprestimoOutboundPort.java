package br.com.elotech.oxy.library.application.ports.outbound.emprestimos;

import br.com.elotech.oxy.library.domain.models.entities.Emprestimo;
import br.com.elotech.oxy.library.domain.models.entities.Livro;

import java.util.List;
import java.util.Optional;

public interface ConsultaEmprestimoOutboundPort {

    Optional<Emprestimo> consultarPorId(Integer id);
    List<Emprestimo> listar();
    boolean verificarExistenciaEmprestimo(Livro livro);

}
