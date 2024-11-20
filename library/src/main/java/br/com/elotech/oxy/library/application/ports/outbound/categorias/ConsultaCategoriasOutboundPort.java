package br.com.elotech.oxy.library.application.ports.outbound.categorias;

import br.com.elotech.oxy.library.domain.models.entities.Categoria;

import java.util.List;
import java.util.Optional;

public interface ConsultaCategoriasOutboundPort {

    Optional<Categoria> consultarPorId(Integer id);
    Optional<Categoria> consultarPorNome(String nome);
    List<Categoria> listar();

}
