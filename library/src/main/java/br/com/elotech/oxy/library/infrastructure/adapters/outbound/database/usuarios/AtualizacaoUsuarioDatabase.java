package br.com.elotech.oxy.library.infrastructure.adapters.outbound.database.usuarios;

import br.com.elotech.oxy.library.application.ports.outbound.usuarios.AtualizacaoUsuarioOutboundPort;
import br.com.elotech.oxy.library.domain.models.entities.Usuario;
import br.com.elotech.oxy.library.infrastructure.adapters.outbound.database.usuarios.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

@Component
public class AtualizacaoUsuarioDatabase implements AtualizacaoUsuarioOutboundPort {

    private final UsuarioRepository repository;

    public AtualizacaoUsuarioDatabase(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public void atualizar(Usuario usuario) {
        repository.save(usuario);
    }
}
