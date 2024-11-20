package br.com.elotech.oxy.library.domain.usecases.usuarios;

import br.com.elotech.oxy.library.application.ports.inbound.usuarios.ConsultaUsuarioUseCase;
import br.com.elotech.oxy.library.application.ports.outbound.usuarios.ConsultaUsuariosOutboundPort;
import br.com.elotech.oxy.library.domain.exception.RecursoNaoEncontradoException;
import br.com.elotech.oxy.library.domain.models.entities.Usuario;
import org.springframework.stereotype.Service;

@Service
public class ConsultaUsuarioUseCaseImpl implements ConsultaUsuarioUseCase {

    private final ConsultaUsuariosOutboundPort consultaUsuariosOutboundPort;

    public ConsultaUsuarioUseCaseImpl(ConsultaUsuariosOutboundPort consultaUsuariosOutboundPort) {
        this.consultaUsuariosOutboundPort = consultaUsuariosOutboundPort;
    }

    @Override
    public Usuario consultarPorId(Integer id) {
        return consultaUsuariosOutboundPort.consultarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(id, Usuario.class));
    }
}
