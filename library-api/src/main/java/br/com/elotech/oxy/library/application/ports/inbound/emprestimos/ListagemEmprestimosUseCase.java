package br.com.elotech.oxy.library.application.ports.inbound.emprestimos;

import br.com.elotech.oxy.library.domain.models.entities.Emprestimo;

import java.util.List;

public interface ListagemEmprestimosUseCase {

    List<Emprestimo> listar();

}
