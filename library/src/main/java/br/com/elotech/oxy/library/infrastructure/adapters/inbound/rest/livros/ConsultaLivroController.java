package br.com.elotech.oxy.library.infrastructure.adapters.inbound.rest.livros;

import br.com.elotech.oxy.library.application.dtos.output.LivroResponse;
import br.com.elotech.oxy.library.application.mappers.LivroMapper;
import br.com.elotech.oxy.library.application.ports.inbound.livros.ConsultaLivroUseCase;
import br.com.elotech.oxy.library.domain.models.entities.Livro;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
public class ConsultaLivroController {

    private final ConsultaLivroUseCase useCase;
    private final LivroMapper mapper;

    public ConsultaLivroController(ConsultaLivroUseCase useCase, LivroMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponse> buscar(@PathVariable @NotNull Integer id) {

        Livro livroEncontrado = useCase.consultarPorId(id);

        return ResponseEntity.ok(mapper.toLivroResponse(livroEncontrado));
    }
}
