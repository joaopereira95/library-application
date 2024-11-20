package br.com.elotech.oxy.library.infrastructure.adapters.outbound.database.usuarios;

import br.com.elotech.oxy.library.application.ports.outbound.usuarios.ExclusaoUsuarioOutboundPort;
import br.com.elotech.oxy.library.domain.models.entities.Usuario;
import br.com.elotech.oxy.library.infrastructure.adapters.outbound.database.usuarios.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

@Component
public class ExclusaoUsuarioDatabase implements ExclusaoUsuarioOutboundPort {

    private final UsuarioRepository repository;

    public ExclusaoUsuarioDatabase(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public void excluir(Usuario usuario) {
        repository.delete(usuario);
    }
}
