package br.com.elotech.oxy.library.infrastructure.adapters.inbound.rest.livros;

import br.com.elotech.oxy.library.application.dtos.output.LivroOnlineResponse;
import br.com.elotech.oxy.library.application.mappers.LivroMapper;
import br.com.elotech.oxy.library.application.ports.inbound.livros.ConsultaLivroOnlinePorIdUseCase;
import br.com.elotech.oxy.library.domain.models.LivroOnline;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Livros")
@RestController
@RequestMapping("/livros/online")
@Validated
public class ConsultaLivroOnlinePorIdController {

    private final ConsultaLivroOnlinePorIdUseCase useCase;
    private final LivroMapper mapper;

    public ConsultaLivroOnlinePorIdController(ConsultaLivroOnlinePorIdUseCase useCase, LivroMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @Operation(
            summary = "Consultar livro online pelo ID",
            description = "Consultar um livro pelo ID na API Google Books")
    @GetMapping("/{id}")
    public ResponseEntity<LivroOnlineResponse> buscarPorId(@PathVariable @NotBlank @Size(min = 12, max = 12) String id) {

        LivroOnline livroEncontrado = useCase.consultar(id);

        return ResponseEntity.ok(mapper.toLivroOnlineResponse(livroEncontrado));
    }
}
