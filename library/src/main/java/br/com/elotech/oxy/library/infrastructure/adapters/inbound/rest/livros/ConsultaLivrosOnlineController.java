package br.com.elotech.oxy.library.infrastructure.adapters.inbound.rest.livros;

import br.com.elotech.oxy.library.application.dtos.output.LivroOnlineResponse;
import br.com.elotech.oxy.library.application.mappers.LivroMapper;
import br.com.elotech.oxy.library.application.ports.inbound.livros.ConsultaLivrosOnlineUseCase;
import br.com.elotech.oxy.library.domain.models.LivroOnline;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/livros/online")
public class ConsultaLivrosOnlineController {

    private final ConsultaLivrosOnlineUseCase useCase;
    private final LivroMapper mapper;

    public ConsultaLivrosOnlineController(ConsultaLivrosOnlineUseCase useCase, LivroMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<LivroOnlineResponse>> buscarOnline(@RequestParam @NotBlank String titulo) {

        List<LivroOnline> livrosEncontrados = useCase.consultarPorTitulo(titulo);

        return ResponseEntity.ok(mapper.toLivroOnlineResponseList(livrosEncontrados));
    }
}
