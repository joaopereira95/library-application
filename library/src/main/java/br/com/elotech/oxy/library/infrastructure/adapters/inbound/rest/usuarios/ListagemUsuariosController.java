package br.com.elotech.oxy.library.infrastructure.adapters.inbound.rest.usuarios;

import br.com.elotech.oxy.library.application.dtos.output.UsuarioResponse;
import br.com.elotech.oxy.library.application.mappers.UsuarioMapper;
import br.com.elotech.oxy.library.application.ports.inbound.usuarios.ListagemUsuariosUseCase;
import br.com.elotech.oxy.library.domain.models.entities.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class ListagemUsuariosController {

    private final ListagemUsuariosUseCase useCase;
    private final UsuarioMapper mapper;

    public ListagemUsuariosController(ListagemUsuariosUseCase useCase, UsuarioMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listar() {

        List<Usuario> usuarios = useCase.listar();

        return ResponseEntity.ok(mapper.toUsuarioResponseList(usuarios));
    }
}
