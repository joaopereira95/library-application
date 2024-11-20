package br.com.elotech.oxy.library.infrastructure.adapters.outbound.database.categorias;

import br.com.elotech.oxy.library.application.ports.outbound.categorias.ConsultaCategoriasOutboundPort;
import br.com.elotech.oxy.library.domain.models.entities.Categoria;
import br.com.elotech.oxy.library.infrastructure.adapters.outbound.database.categorias.repository.CategoriaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ConsultaCategoriasDatabase implements ConsultaCategoriasOutboundPort {

    private final CategoriaRepository repository;

    public ConsultaCategoriasDatabase(CategoriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Categoria> consultarPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Categoria> consultarPorNome(String nome) {
        return repository.findFirstByNome(nome);
    }

    @Override
    public List<Categoria> listar() {
        return repository.findAllByOrderByIdDesc();
    }
}
