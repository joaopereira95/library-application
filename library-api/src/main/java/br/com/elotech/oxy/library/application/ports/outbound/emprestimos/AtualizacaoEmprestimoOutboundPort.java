package br.com.elotech.oxy.library.application.ports.outbound.emprestimos;

import br.com.elotech.oxy.library.domain.models.entities.Emprestimo;

public interface AtualizacaoEmprestimoOutboundPort {

    void atualizar(Emprestimo emprestimo);

}