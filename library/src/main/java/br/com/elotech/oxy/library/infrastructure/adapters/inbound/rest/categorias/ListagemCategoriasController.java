package br.com.elotech.oxy.library.infrastructure.adapters.inbound.rest.categorias;

import br.com.elotech.oxy.library.application.dtos.output.CategoriaResponse;
import br.com.elotech.oxy.library.application.mappers.CategoriaMapper;
import br.com.elotech.oxy.library.application.ports.inbound.categorias.ListagemCategoriasUseCase;
import br.com.elotech.oxy.library.domain.models.entities.Categoria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class ListagemCategoriasController {

    private final ListagemCategoriasUseCase useCase;
    private final CategoriaMapper mapper;

    public ListagemCategoriasController(ListagemCategoriasUseCase useCase, CategoriaMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> listar() {

        List<Categoria> categorias = useCase.listar();

        return ResponseEntity.ok(mapper.toCategoriaResponseList(categorias));
    }
}
