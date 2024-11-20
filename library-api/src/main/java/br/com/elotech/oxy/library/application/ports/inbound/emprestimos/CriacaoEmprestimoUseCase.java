package br.com.elotech.oxy.library.application.ports.inbound.emprestimos;

import br.com.elotech.oxy.library.domain.models.entities.Emprestimo;

public interface CriacaoEmprestimoUseCase {

    Emprestimo criar(Emprestimo emprestimo);

}
