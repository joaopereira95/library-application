package br.com.elotech.oxy.library.application.ports.inbound.livros;

import br.com.elotech.oxy.library.domain.models.entities.Livro;

public interface ConsultaLivroUseCase {

    Livro consultarPorId(Integer id);

}
