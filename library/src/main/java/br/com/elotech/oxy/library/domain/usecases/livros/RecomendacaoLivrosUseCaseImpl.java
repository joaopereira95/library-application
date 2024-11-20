package br.com.elotech.oxy.library.domain.usecases.livros;

import br.com.elotech.oxy.library.application.ports.inbound.livros.RecomendacaoLivrosUseCase;
import br.com.elotech.oxy.library.application.ports.outbound.livros.ConsultaLivrosOutboundPort;
import br.com.elotech.oxy.library.application.ports.outbound.usuarios.ConsultaUsuariosOutboundPort;
import br.com.elotech.oxy.library.domain.exception.RecursoNaoEncontradoException;
import br.com.elotech.oxy.library.domain.models.entities.Livro;
import br.com.elotech.oxy.library.domain.models.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecomendacaoLivrosUseCaseImpl implements RecomendacaoLivrosUseCase {

    private final ConsultaLivrosOutboundPort consultaLivrosOutboundPort;
    private final ConsultaUsuariosOutboundPort consultaUsuariosOutboundPort;

    public RecomendacaoLivrosUseCaseImpl(ConsultaLivrosOutboundPort consultaLivrosOutboundPort, ConsultaUsuariosOutboundPort consultaUsuariosOutboundPort) {
        this.consultaLivrosOutboundPort = consultaLivrosOutboundPort;
        this.consultaUsuariosOutboundPort = consultaUsuariosOutboundPort;
    }

    @Override
    public List<Livro> recomendarParaUsuario(Integer usuarioId) {
        Usuario usuario = consultaUsuariosOutboundPort.consultarPorId(usuarioId)
                .orElseThrow(() -> new RecursoNaoEncontradoException(usuarioId, Usuario.class));

        return consultaLivrosOutboundPort.listarRecomendacoes(usuario);
    }
}
