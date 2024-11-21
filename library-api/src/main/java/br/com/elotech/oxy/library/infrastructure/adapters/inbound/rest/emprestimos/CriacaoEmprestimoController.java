package br.com.elotech.oxy.library.infrastructure.adapters.inbound.rest.emprestimos;

import br.com.elotech.oxy.library.application.dtos.input.EmprestimoRequest;
import br.com.elotech.oxy.library.application.dtos.output.EmprestimoResponse;
import br.com.elotech.oxy.library.application.mappers.EmprestimoMapper;
import br.com.elotech.oxy.library.application.ports.inbound.emprestimos.CriacaoEmprestimoUseCase;
import br.com.elotech.oxy.library.domain.models.entities.Emprestimo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Empréstimos")
@RestController
@RequestMapping("/emprestimos")
public class CriacaoEmprestimoController {

    private final CriacaoEmprestimoUseCase useCase;
    private final EmprestimoMapper mapper;

    public CriacaoEmprestimoController(CriacaoEmprestimoUseCase criacaoEmprestimoUseCase, EmprestimoMapper mapper) {
        this.useCase = criacaoEmprestimoUseCase;
        this.mapper = mapper;
    }

    @Operation(
            summary = "Cadastrar empréstimo",
            description = "Cadastra um empréstimo")
    @PostMapping
    public ResponseEntity<EmprestimoResponse> realizar(@Valid @RequestBody EmprestimoRequest novoEmprestimo) {

        Emprestimo emprestimoRealizado = useCase.criar(mapper.toModel(novoEmprestimo));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toEmprestimoResponse(emprestimoRealizado));
    }

}
