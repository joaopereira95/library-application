package br.com.elotech.oxy.library.domain.usecases.usuarios;

import br.com.elotech.oxy.library.application.ports.inbound.usuarios.AtualizacaoUsuarioUseCase;
import br.com.elotech.oxy.library.application.ports.outbound.usuarios.AtualizacaoUsuarioOutboundPort;
import br.com.elotech.oxy.library.application.ports.outbound.usuarios.ConsultaUsuariosOutboundPort;
import br.com.elotech.oxy.library.domain.exception.RecursoNaoEncontradoException;
import br.com.elotech.oxy.library.domain.models.entities.Usuario;
import org.springframework.stereotype.Service;

@Service
public class AtualizacaoUsuarioUseCaseImpl implements AtualizacaoUsuarioUseCase {

    private final AtualizacaoUsuarioOutboundPort outboundPort;
    private final ConsultaUsuariosOutboundPort consultarUsuarioPort;

    public AtualizacaoUsuarioUseCaseImpl(AtualizacaoUsuarioOutboundPort outboundPort, ConsultaUsuariosOutboundPort consultarUsuarioPort) {
        this.outboundPort = outboundPort;
        this.consultarUsuarioPort = consultarUsuarioPort;
    }

    @Override
    public void atualizar(Integer id, Usuario novoUsuario) {

        Usuario usuarioExistente = consultarUsuarioPort.consultarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(id, Usuario.class));

        usuarioExistente.setNome(novoUsuario.getNome());
        usuarioExistente.setEmail(novoUsuario.getEmail());
        usuarioExistente.setTelefone(novoUsuario.getTelefone());

        outboundPort.atualizar(usuarioExistente);
    }
}
