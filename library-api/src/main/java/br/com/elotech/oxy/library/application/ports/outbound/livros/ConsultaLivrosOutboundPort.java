package br.com.elotech.oxy.library.application.ports.outbound.livros;

import br.com.elotech.oxy.library.domain.models.entities.Livro;
import br.com.elotech.oxy.library.domain.models.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface ConsultaLivrosOutboundPort {

    Optional<Livro> consultarPorId(Integer id);
    List<Livro> listar();
    List<Livro> listarRecomendacoes(Usuario usuario);
    Optional<Livro> consultarPorIsbn(String isbn);

}
