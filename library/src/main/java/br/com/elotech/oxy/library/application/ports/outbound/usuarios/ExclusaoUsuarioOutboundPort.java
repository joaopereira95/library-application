package br.com.elotech.oxy.library.application.ports.outbound.usuarios;

import br.com.elotech.oxy.library.domain.models.entities.Usuario;

public interface ExclusaoUsuarioOutboundPort {

    void excluir(Usuario usuario);

}
