package br.com.elotech.oxy.library.application.ports.inbound.livros;

import br.com.elotech.oxy.library.domain.models.LivroOnline;

public interface ConsultaLivroOnlinePorIdUseCase {

    LivroOnline consultar(String id);

}
