package br.com.elotech.oxy.library.infrastructure.adapters.inbound.rest.emprestimos;

import br.com.elotech.oxy.library.application.ports.inbound.emprestimos.DevolucaoUseCase;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emprestimos")
public class DevolucaoController {

    private final DevolucaoUseCase useCase;

    public DevolucaoController(DevolucaoUseCase useCase) {
        this.useCase = useCase;
    }

    @PutMapping("/devolucao/{id}")
    public ResponseEntity<Void> realizarDevolucao(@PathVariable @NotNull Integer id) {

        useCase.devolver(id);

        return ResponseEntity.ok().build();
    }
}
