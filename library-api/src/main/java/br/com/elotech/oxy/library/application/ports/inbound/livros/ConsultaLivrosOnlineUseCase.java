package br.com.elotech.oxy.library.application.ports.inbound.livros;

import br.com.elotech.oxy.library.domain.models.LivroOnline;

import java.util.List;

public interface ConsultaLivrosOnlineUseCase {

    List<LivroOnline> consultarPorTitulo(String titulo);

}
