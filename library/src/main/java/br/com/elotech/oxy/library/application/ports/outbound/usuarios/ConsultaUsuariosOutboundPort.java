package br.com.elotech.oxy.library.application.ports.outbound.usuarios;

import br.com.elotech.oxy.library.domain.models.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface ConsultaUsuariosOutboundPort {

    Optional<Usuario> consultarPorId(Integer id);
    List<Usuario> listar();

}
