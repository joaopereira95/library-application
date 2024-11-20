package br.com.elotech.oxy.library.application.ports.inbound.categorias;

import br.com.elotech.oxy.library.domain.models.entities.Categoria;

import java.util.List;

public interface ListagemCategoriasUseCase {

    List<Categoria> listar();
}
