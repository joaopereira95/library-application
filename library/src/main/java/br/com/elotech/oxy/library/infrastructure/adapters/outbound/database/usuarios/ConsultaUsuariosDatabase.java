package br.com.elotech.oxy.library.infrastructure.adapters.outbound.database.usuarios;

import br.com.elotech.oxy.library.application.ports.outbound.usuarios.ConsultaUsuariosOutboundPort;
import br.com.elotech.oxy.library.domain.models.entities.Usuario;
import br.com.elotech.oxy.library.infrastructure.adapters.outbound.database.usuarios.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ConsultaUsuariosDatabase implements ConsultaUsuariosOutboundPort {

    private final UsuarioRepository repository;

    public ConsultaUsuariosDatabase(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Usuario> consultarPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Usuario> listar() {
        return repository.findAllByOrderByIdDesc();
    }
}
