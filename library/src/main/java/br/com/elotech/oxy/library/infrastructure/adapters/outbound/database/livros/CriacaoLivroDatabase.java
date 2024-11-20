package br.com.elotech.oxy.library.infrastructure.adapters.outbound.database.livros;

import br.com.elotech.oxy.library.application.ports.outbound.livros.CriacaoLivroOutboundPort;
import br.com.elotech.oxy.library.domain.models.entities.Livro;
import br.com.elotech.oxy.library.infrastructure.adapters.outbound.database.livros.repository.LivroRepository;
import org.springframework.stereotype.Component;

@Component
public class CriacaoLivroDatabase implements CriacaoLivroOutboundPort {

    private final LivroRepository repository;

    public CriacaoLivroDatabase(LivroRepository repository) {
        this.repository = repository;
    }

    @Override
    public Livro criar(Livro livro) {
        return repository.save(livro);
    }
}
