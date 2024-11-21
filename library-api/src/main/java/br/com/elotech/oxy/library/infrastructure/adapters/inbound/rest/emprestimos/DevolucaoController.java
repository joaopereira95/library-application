package br.com.elotech.oxy.library.infrastructure.adapters.inbound.rest.emprestimos;

import br.com.elotech.oxy.library.application.ports.inbound.emprestimos.DevolucaoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Empréstimos")
@RestController
@RequestMapping("/emprestimos")
public class DevolucaoController {

    private final DevolucaoUseCase useCase;

    public DevolucaoController(DevolucaoUseCase useCase) {
        this.useCase = useCase;
    }

    @Operation(
            summary = "Realizar devolução de empréstimo",
            description = "Realiza a devolução de um empréstimo por ID")
    @PutMapping("/devolucao/{id}")
    public ResponseEntity<Void> realizarDevolucao(@PathVariable @NotNull Integer id) {

        useCase.devolver(id);

        return ResponseEntity.ok().build();
    }
}
