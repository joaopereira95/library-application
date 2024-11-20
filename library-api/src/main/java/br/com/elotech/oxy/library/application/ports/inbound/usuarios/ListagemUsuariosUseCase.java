package br.com.elotech.oxy.library.application.ports.inbound.usuarios;

import br.com.elotech.oxy.library.domain.models.entities.Usuario;

import java.util.List;

public interface ListagemUsuariosUseCase {

    List<Usuario> listar();
}
