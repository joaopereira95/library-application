package br.com.elotech.oxy.library.infrastructure.adapters.inbound.rest.livros;

import br.com.elotech.oxy.library.application.dtos.input.LivroRequest;
import br.com.elotech.oxy.library.application.dtos.output.LivroResponse;
import br.com.elotech.oxy.library.application.mappers.LivroMapper;
import br.com.elotech.oxy.library.application.ports.inbound.livros.CriacaoLivroUseCase;
import br.com.elotech.oxy.library.domain.models.entities.Livro;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Livros")
@RestController
@RequestMapping("/livros")
public class CriacaoLivroController {

    private final CriacaoLivroUseCase useCase;
    private final LivroMapper mapper;

    public CriacaoLivroController(CriacaoLivroUseCase useCase, LivroMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @Operation(
            summary = "Cadastrar livro",
            description = "Cadastra um livro")
    @PostMapping
    public ResponseEntity<LivroResponse> inserir(@Valid @RequestBody LivroRequest novoLivro) {

        Livro livroCadastrado = useCase.criar(mapper.toModel(novoLivro));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toLivroResponse(livroCadastrado));
    }

}
