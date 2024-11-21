package br.com.elotech.oxy.library.infrastructure.adapters.inbound.rest.livros;

import br.com.elotech.oxy.library.application.dtos.output.LivroOnlineResponse;
import br.com.elotech.oxy.library.application.mappers.LivroMapper;
import br.com.elotech.oxy.library.application.ports.inbound.livros.ConsultaLivrosOnlineUseCase;
import br.com.elotech.oxy.library.domain.models.LivroOnline;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Livros")
@RestController
@RequestMapping("/livros/online")
public class ConsultaLivrosOnlineController {

    private final ConsultaLivrosOnlineUseCase useCase;
    private final LivroMapper mapper;

    public ConsultaLivrosOnlineController(ConsultaLivrosOnlineUseCase useCase, LivroMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @Operation(
            summary = "Consultar livros online pelo título",
            description = "Consulta os livros pelo título na API Google Books")
    @GetMapping
    public ResponseEntity<List<LivroOnlineResponse>> buscarOnline(@RequestParam @NotBlank String titulo) {

        List<LivroOnline> livrosEncontrados = useCase.consultarPorTitulo(titulo);

        return ResponseEntity.ok(mapper.toLivroOnlineResponseList(livrosEncontrados));
    }
}
