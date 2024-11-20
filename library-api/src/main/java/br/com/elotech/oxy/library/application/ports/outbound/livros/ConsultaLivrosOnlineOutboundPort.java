package br.com.elotech.oxy.library.application.ports.outbound.livros;

import br.com.elotech.oxy.library.domain.models.LivroOnline;

import java.util.List;
import java.util.Optional;

public interface ConsultaLivrosOnlineOutboundPort {

    List<LivroOnline> consultarPorTitulo(String titulo);

    Optional<LivroOnline> consultarPorId(String id);

}
