package br.com.elotech.oxy.library.infrastructure.adapters.inbound.rest.livros;

import br.com.elotech.oxy.library.application.dtos.input.LivroRequest;
import br.com.elotech.oxy.library.application.mappers.LivroMapper;
import br.com.elotech.oxy.library.application.ports.inbound.livros.AtualizacaoLivroUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Livros")
@RestController
@RequestMapping("/livros")
public class AtualizacaoLivroController {

    private final AtualizacaoLivroUseCase useCase;
    private final LivroMapper mapper;

    public AtualizacaoLivroController(AtualizacaoLivroUseCase useCase, LivroMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @Operation(
            summary = "Atualizar livro",
            description = "Atualiza um livro na base de dados")
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Integer id, @Valid @RequestBody LivroRequest livroAtualizado) {

        useCase.atualizar(id, mapper.toModel(livroAtualizado));

        return ResponseEntity.ok().build();
    }

}
