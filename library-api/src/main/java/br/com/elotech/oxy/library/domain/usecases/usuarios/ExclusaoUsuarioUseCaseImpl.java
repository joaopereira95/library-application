package br.com.elotech.oxy.library.domain.usecases.usuarios;

import br.com.elotech.oxy.library.application.ports.inbound.usuarios.ExclusaoUsuarioUseCase;
import br.com.elotech.oxy.library.application.ports.outbound.usuarios.ConsultaUsuariosOutboundPort;
import br.com.elotech.oxy.library.application.ports.outbound.usuarios.ExclusaoUsuarioOutboundPort;
import br.com.elotech.oxy.library.domain.exception.RecursoNaoEncontradoException;
import br.com.elotech.oxy.library.domain.models.entities.Usuario;
import org.springframework.stereotype.Service;

@Service
public class ExclusaoUsuarioUseCaseImpl implements ExclusaoUsuarioUseCase {

    private final ExclusaoUsuarioOutboundPort outboundPort;
    private final ConsultaUsuariosOutboundPort consultarUsuarioPort;

    public ExclusaoUsuarioUseCaseImpl(ExclusaoUsuarioOutboundPort outboundPort,
                                      ConsultaUsuariosOutboundPort consultarUsuarioPort) {

        this.outboundPort = outboundPort;
        this.consultarUsuarioPort = consultarUsuarioPort;
    }

    @Override
    public void excluir(Integer id) {
        Usuario usuario = consultarUsuarioPort.consultarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(id, Usuario.class));

        outboundPort.excluir(usuario);
    }
}
