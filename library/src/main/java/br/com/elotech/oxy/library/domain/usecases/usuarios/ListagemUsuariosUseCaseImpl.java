package br.com.elotech.oxy.library.domain.usecases.usuarios;

import br.com.elotech.oxy.library.application.ports.inbound.usuarios.ListagemUsuariosUseCase;
import br.com.elotech.oxy.library.application.ports.outbound.usuarios.ConsultaUsuariosOutboundPort;
import br.com.elotech.oxy.library.domain.models.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListagemUsuariosUseCaseImpl implements ListagemUsuariosUseCase {

    private final ConsultaUsuariosOutboundPort consultaUsuariosOutboundPort;

    public ListagemUsuariosUseCaseImpl(ConsultaUsuariosOutboundPort consultaUsuariosOutboundPort) {
        this.consultaUsuariosOutboundPort = consultaUsuariosOutboundPort;
    }

    @Override
    public List<Usuario> listar() {
        return consultaUsuariosOutboundPort.listar();
    }
}
