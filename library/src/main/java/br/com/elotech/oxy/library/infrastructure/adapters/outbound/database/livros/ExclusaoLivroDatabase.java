package br.com.elotech.oxy.library.infrastructure.adapters.outbound.database.livros;

import br.com.elotech.oxy.library.application.ports.outbound.livros.ExclusaoLivroOutboundPort;
import br.com.elotech.oxy.library.domain.models.entities.Livro;
import br.com.elotech.oxy.library.infrastructure.adapters.outbound.database.livros.repository.LivroRepository;
import org.springframework.stereotype.Component;

@Component
public class ExclusaoLivroDatabase implements ExclusaoLivroOutboundPort {

    private final LivroRepository repository;

    public ExclusaoLivroDatabase(LivroRepository repository) {
        this.repository = repository;
    }

    @Override
    public void excluir(Livro livro) {
        repository.delete(livro);
    }
}
