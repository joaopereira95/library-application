package br.com.elotech.oxy.library.application.mappers;

import br.com.elotech.oxy.library.application.dtos.output.CategoriaResponse;
import br.com.elotech.oxy.library.domain.models.entities.Categoria;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoriaMapper {

    public List<CategoriaResponse> toCategoriaResponseList(List<Categoria> categorias) {
        return categorias.stream().map(this::toCategoriaResponse).toList();
    }


    public CategoriaResponse toCategoriaResponse(Categoria categoria) {
        return new CategoriaResponse(
                categoria.getId(),
                categoria.getNome()
        );
    }

}
