package br.com.elotech.oxy.library.infrastructure.adapters.inbound.rest.emprestimos;

import br.com.elotech.oxy.library.application.dtos.output.EmprestimoResponse;
import br.com.elotech.oxy.library.application.mappers.EmprestimoMapper;
import br.com.elotech.oxy.library.application.ports.inbound.emprestimos.ConsultaEmprestimoUseCase;
import br.com.elotech.oxy.library.domain.models.entities.Emprestimo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Empréstimos")
@RestController
@RequestMapping("/emprestimos")
public class ConsultaEmprestimoController {

    private final ConsultaEmprestimoUseCase useCase;
    private final EmprestimoMapper mapper;

    public ConsultaEmprestimoController(ConsultaEmprestimoUseCase useCase, EmprestimoMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @Operation(
            summary = "Buscar empréstimo por ID",
            description = "Busca um empréstimo pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoResponse> buscar(@PathVariable @NotNull Integer id) {

        Emprestimo emprestimoEncontrado = useCase.consultarPorId(id);

        return ResponseEntity.ok(mapper.toEmprestimoResponse(emprestimoEncontrado));
    }
}
