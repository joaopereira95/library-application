package br.com.elotech.oxy.library.infrastructure.adapters.inbound.rest.usuarios;

import br.com.elotech.oxy.library.application.dtos.input.UsuarioRequest;
import br.com.elotech.oxy.library.application.dtos.output.UsuarioResponse;
import br.com.elotech.oxy.library.application.mappers.UsuarioMapper;
import br.com.elotech.oxy.library.application.ports.inbound.usuarios.CriacaoUsuarioUseCase;
import br.com.elotech.oxy.library.domain.models.entities.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Usuários")
@RestController
@RequestMapping("/usuarios")
public class CriacaoUsuarioController {

    private final CriacaoUsuarioUseCase useCase;
    private final UsuarioMapper mapper;

    public CriacaoUsuarioController(CriacaoUsuarioUseCase useCase, UsuarioMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @Operation(
            summary = "Cadastrar usuario",
            description = "Cadastra um usuário")
    @PostMapping
    public ResponseEntity<UsuarioResponse> inserir(@Valid @RequestBody UsuarioRequest novoUsuario) {

        Usuario usuarioCadastrado = useCase.criar(mapper.toModel(novoUsuario));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toUsuarioResponse(usuarioCadastrado));
    }

}
