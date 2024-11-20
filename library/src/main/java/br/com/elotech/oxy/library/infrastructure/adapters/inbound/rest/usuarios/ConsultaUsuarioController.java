package br.com.elotech.oxy.library.infrastructure.adapters.inbound.rest.usuarios;

import br.com.elotech.oxy.library.application.dtos.output.UsuarioResponse;
import br.com.elotech.oxy.library.application.mappers.UsuarioMapper;
import br.com.elotech.oxy.library.application.ports.inbound.usuarios.ConsultaUsuarioUseCase;
import br.com.elotech.oxy.library.domain.models.entities.Usuario;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class ConsultaUsuarioController {

    private final ConsultaUsuarioUseCase useCase;
    private final UsuarioMapper mapper;

    public ConsultaUsuarioController(ConsultaUsuarioUseCase useCase, UsuarioMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscar(@PathVariable @NotNull Integer id) {

        Usuario usuarioEncontrado = useCase.consultarPorId(id);

        return ResponseEntity.ok(mapper.toUsuarioResponse(usuarioEncontrado));
    }
}
