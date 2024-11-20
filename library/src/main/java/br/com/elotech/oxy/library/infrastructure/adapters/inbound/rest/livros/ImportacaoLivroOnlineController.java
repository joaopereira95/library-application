package br.com.elotech.oxy.library.infrastructure.adapters.inbound.rest.livros;

import br.com.elotech.oxy.library.application.dtos.input.LivroOnlineRequest;
import br.com.elotech.oxy.library.application.dtos.output.LivroResponse;
import br.com.elotech.oxy.library.application.mappers.LivroMapper;
import br.com.elotech.oxy.library.application.ports.inbound.livros.ImportacaoLivroOnlineUseCase;
import br.com.elotech.oxy.library.domain.models.entities.Livro;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros/online")
public class ImportacaoLivroOnlineController {

    private final ImportacaoLivroOnlineUseCase useCase;
    private final LivroMapper mapper;

    public ImportacaoLivroOnlineController(ImportacaoLivroOnlineUseCase useCase, LivroMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @PostMapping("/importacao")
    public ResponseEntity<LivroResponse> importar(@Valid @RequestBody LivroOnlineRequest livroOnlineRequest) {

        Livro livroCadastrado = useCase.importarPorId(livroOnlineRequest.id());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toLivroResponse(livroCadastrado));
    }

}
