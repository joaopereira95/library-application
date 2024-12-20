package br.com.elotech.oxy.library.infrastructure.adapters.inbound.rest.livros;

import br.com.elotech.oxy.library.application.ports.inbound.livros.ExclusaoLivroUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Livros")
@RestController
@RequestMapping("/livros")
public class ExclusaoLivroController {

    private final ExclusaoLivroUseCase useCase;

    public ExclusaoLivroController(ExclusaoLivroUseCase useCase) {
        this.useCase = useCase;
    }

    @Operation(
            summary = "Excluir livro",
            description = "Exclui um livro")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {

        useCase.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
