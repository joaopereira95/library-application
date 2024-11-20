package br.com.elotech.oxy.library.infrastructure.adapters.inbound.rest.emprestimos;

import br.com.elotech.oxy.library.application.dtos.output.EmprestimoResponse;
import br.com.elotech.oxy.library.application.mappers.EmprestimoMapper;
import br.com.elotech.oxy.library.application.ports.inbound.emprestimos.ListagemEmprestimosUseCase;
import br.com.elotech.oxy.library.domain.models.entities.Emprestimo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class ListagemEmprestimosController {

    private final ListagemEmprestimosUseCase useCase;
    private final EmprestimoMapper mapper;

    public ListagemEmprestimosController(ListagemEmprestimosUseCase useCase, EmprestimoMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<EmprestimoResponse>> listar() {

        List<Emprestimo> emprestimos = useCase.listar();

        return ResponseEntity.ok(mapper.toEmprestimoResponseList(emprestimos));
    }
}
