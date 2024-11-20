package br.com.elotech.oxy.library.application.ports.inbound.usuarios;

import br.com.elotech.oxy.library.domain.models.entities.Usuario;

public interface ConsultaUsuarioUseCase {

    Usuario consultarPorId(Integer id);

}
