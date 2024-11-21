package br.com.elotech.oxy.library.infrastructure.adapters.inbound.rest.livros;

import br.com.elotech.oxy.library.application.dtos.output.LivroResponse;
import br.com.elotech.oxy.library.application.mappers.LivroMapper;
import br.com.elotech.oxy.library.application.ports.inbound.livros.RecomendacaoLivrosUseCase;
import br.com.elotech.oxy.library.domain.models.entities.Livro;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Livros")
@RestController
@RequestMapping("/livros")
public class RecomendacaoLivrosController {

    private final RecomendacaoLivrosUseCase useCase;
    private final LivroMapper mapper;

    public RecomendacaoLivrosController(RecomendacaoLivrosUseCase useCase, LivroMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @Operation(
            summary = "Recomendar livros para usuário",
            description = "Retorna as recomendações de livros para um usuário")
    @GetMapping("/recomendacoes/{usuarioId}")
    public ResponseEntity<List<LivroResponse>> listar(@PathVariable @NotNull Integer usuarioId) {

        List<Livro> livros = useCase.recomendarParaUsuario(usuarioId);

        return ResponseEntity.ok(mapper.toLivroResponseList(livros));
    }
}
