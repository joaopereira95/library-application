package br.com.elotech.oxy.library.application.ports.inbound.livros;

import br.com.elotech.oxy.library.domain.models.entities.Livro;

import java.util.List;

public interface RecomendacaoLivrosUseCase {

    List<Livro> recomendarParaUsuario(Integer usuarioId);
}
