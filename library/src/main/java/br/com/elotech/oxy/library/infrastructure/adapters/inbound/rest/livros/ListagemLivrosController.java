package br.com.elotech.oxy.library.infrastructure.adapters.inbound.rest.livros;

import br.com.elotech.oxy.library.application.dtos.output.LivroResponse;
import br.com.elotech.oxy.library.application.mappers.LivroMapper;
import br.com.elotech.oxy.library.application.ports.inbound.livros.ListagemLivrosUseCase;
import br.com.elotech.oxy.library.domain.models.entities.Livro;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class ListagemLivrosController {

    private final ListagemLivrosUseCase useCase;
    private final LivroMapper mapper;

    public ListagemLivrosController(ListagemLivrosUseCase useCase, LivroMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<LivroResponse>> listar() {

        List<Livro> livros = useCase.listar();

        return ResponseEntity.ok(mapper.toLivroResponseList(livros));
    }
}
