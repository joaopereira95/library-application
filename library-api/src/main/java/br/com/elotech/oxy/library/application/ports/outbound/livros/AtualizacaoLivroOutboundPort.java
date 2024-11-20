package br.com.elotech.oxy.library.application.ports.outbound.livros;

import br.com.elotech.oxy.library.domain.models.entities.Livro;

public interface AtualizacaoLivroOutboundPort {

    void atualizar(Livro livro);

}
