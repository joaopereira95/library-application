package br.com.elotech.oxy.library.infrastructure.adapters.outbound.database.usuarios;

import br.com.elotech.oxy.library.application.ports.outbound.usuarios.CriacaoUsuarioOutboundPort;
import br.com.elotech.oxy.library.domain.models.entities.Usuario;
import br.com.elotech.oxy.library.infrastructure.adapters.outbound.database.usuarios.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

@Component
public class CriacaoUsuarioDatabase implements CriacaoUsuarioOutboundPort {

    private final UsuarioRepository repository;

    public CriacaoUsuarioDatabase(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Usuario criar(Usuario usuario) {
        return repository.save(usuario);
    }
}
