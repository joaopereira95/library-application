package br.com.elotech.oxy.library.infrastructure.adapters.inbound.rest.usuarios;

import br.com.elotech.oxy.library.application.dtos.input.UsuarioRequest;
import br.com.elotech.oxy.library.application.mappers.UsuarioMapper;
import br.com.elotech.oxy.library.application.ports.inbound.usuarios.AtualizacaoUsuarioUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class AtualizacaoUsuarioController {

    private final AtualizacaoUsuarioUseCase useCase;
    private final UsuarioMapper mapper;

    public AtualizacaoUsuarioController(AtualizacaoUsuarioUseCase useCase, UsuarioMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Integer id, @Valid @RequestBody UsuarioRequest usuarioAtualizado) {

        useCase.atualizar(id, mapper.toModel(usuarioAtualizado));

        return ResponseEntity.ok().build();
    }

}
