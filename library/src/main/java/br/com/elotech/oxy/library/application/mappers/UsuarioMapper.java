package br.com.elotech.oxy.library.application.mappers;

import br.com.elotech.oxy.library.application.dtos.input.UsuarioRequest;
import br.com.elotech.oxy.library.application.dtos.output.UsuarioResponse;
import br.com.elotech.oxy.library.domain.models.entities.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioMapper {

    public Usuario toModel(UsuarioRequest inserirUsuarioRequest) {
        return new Usuario(
                inserirUsuarioRequest.nome(),
                inserirUsuarioRequest.email(),
                inserirUsuarioRequest.telefone()
        );
    }

    public List<UsuarioResponse> toUsuarioResponseList(List<Usuario> usuarios) {
        return usuarios.stream().map(this::toUsuarioResponse).toList();
    }

    public UsuarioResponse toUsuarioResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getDataCadastro(),
                usuario.getTelefone()
        );
    }
}
