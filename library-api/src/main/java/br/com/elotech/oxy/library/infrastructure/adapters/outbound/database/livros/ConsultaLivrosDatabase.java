package br.com.elotech.oxy.library.infrastructure.adapters.outbound.database.livros;

import br.com.elotech.oxy.library.application.ports.outbound.livros.ConsultaLivrosOutboundPort;
import br.com.elotech.oxy.library.domain.models.entities.Livro;
import br.com.elotech.oxy.library.domain.models.entities.Usuario;
import br.com.elotech.oxy.library.infrastructure.adapters.outbound.database.livros.repository.LivroRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ConsultaLivrosDatabase implements ConsultaLivrosOutboundPort {

    private final LivroRepository repository;

    public ConsultaLivrosDatabase(LivroRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Livro> consultarPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Livro> listar() {
        return repository.findAllByOrderByIdDesc();
    }

    @Override
    public List<Livro> listarRecomendacoes(Usuario usuario) {
        return repository.buscarRecomendacoesDeLivros(usuario.getId());
    }

    @Override
    public Optional<Livro> consultarPorIsbn(String isbn) {
        return repository.findByIsbn(isbn);
    }


}
