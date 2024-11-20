package br.com.elotech.oxy.library.infrastructure.adapters.outbound.database.livros;

import br.com.elotech.oxy.library.application.ports.outbound.livros.AtualizacaoLivroOutboundPort;
import br.com.elotech.oxy.library.domain.models.entities.Livro;
import br.com.elotech.oxy.library.infrastructure.adapters.outbound.database.livros.repository.LivroRepository;
import org.springframework.stereotype.Component;

@Component
public class AtualizacaoLivroDatabase implements AtualizacaoLivroOutboundPort {

    private final LivroRepository repository;

    public AtualizacaoLivroDatabase(LivroRepository repository) {
        this.repository = repository;
    }

    @Override
    public void atualizar(Livro livro) {
        repository.save(livro);
    }
}
