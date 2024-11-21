package br.com.elotech.oxy.library.infrastructure.adapters.inbound.rest.usuarios;

import br.com.elotech.oxy.library.application.ports.inbound.usuarios.ExclusaoUsuarioUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Usuários")
@RestController
@RequestMapping("/usuarios")
public class ExclusaoUsuarioController {

    private final ExclusaoUsuarioUseCase useCase;

    public ExclusaoUsuarioController(ExclusaoUsuarioUseCase useCase) {
        this.useCase = useCase;
    }

    @Operation(
            summary = "Excluir usuário",
            description = "Exclui um usuário")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {

        useCase.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
