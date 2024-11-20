package br.com.elotech.oxy.library.domain.usecases.usuarios;

import br.com.elotech.oxy.library.application.ports.inbound.usuarios.CriacaoUsuarioUseCase;
import br.com.elotech.oxy.library.application.ports.outbound.usuarios.CriacaoUsuarioOutboundPort;
import br.com.elotech.oxy.library.domain.exception.RegraNegocioException;
import br.com.elotech.oxy.library.domain.models.entities.Usuario;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CriacaoUsuarioUseCaseImpl implements CriacaoUsuarioUseCase {

    private final CriacaoUsuarioOutboundPort outboundPort;

    public CriacaoUsuarioUseCaseImpl(CriacaoUsuarioOutboundPort outboundPort) {
        this.outboundPort = outboundPort;
    }

    @Override
    public Usuario criar(Usuario novoUsuario) {

        if (LocalDate.now().isBefore(novoUsuario.getDataCadastro())) {
            throw new RegraNegocioException("Data de cadastro não pode ser maior que a data atual.");
        }

        return outboundPort.criar(novoUsuario);
    }
}
