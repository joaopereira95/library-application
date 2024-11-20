package br.com.elotech.oxy.library.domain.usecases.emprestimos;

import br.com.elotech.oxy.library.application.ports.inbound.emprestimos.CriacaoEmprestimoUseCase;
import br.com.elotech.oxy.library.application.ports.outbound.emprestimos.ConsultaEmprestimoOutboundPort;
import br.com.elotech.oxy.library.application.ports.outbound.emprestimos.CriacaoEmprestimoOutboundPort;
import br.com.elotech.oxy.library.application.ports.outbound.livros.ConsultaLivrosOutboundPort;
import br.com.elotech.oxy.library.application.ports.outbound.usuarios.ConsultaUsuariosOutboundPort;
import br.com.elotech.oxy.library.domain.exception.RecursoNaoEncontradoException;
import br.com.elotech.oxy.library.domain.exception.RegraNegocioException;
import br.com.elotech.oxy.library.domain.models.entities.Emprestimo;
import br.com.elotech.oxy.library.domain.models.entities.Livro;
import br.com.elotech.oxy.library.domain.models.entities.Usuario;
import org.springframework.stereotype.Service;

@Service
public class CriacaoEmprestimoUseCaseImpl implements CriacaoEmprestimoUseCase {

    private final CriacaoEmprestimoOutboundPort criacaoEmprestimoOutboundPort;
    private final ConsultaEmprestimoOutboundPort consultaEmprestimoOutboundPort;
    private final ConsultaUsuariosOutboundPort consultaUsuariosOutboundPort;
    private final ConsultaLivrosOutboundPort consultaLivrosOutboundPort;

    public CriacaoEmprestimoUseCaseImpl(CriacaoEmprestimoOutboundPort criacaoEmprestimoOutboundPort, ConsultaEmprestimoOutboundPort consultaEmprestimoOutboundPort, ConsultaUsuariosOutboundPort consultaUsuariosOutboundPort, ConsultaLivrosOutboundPort consultaLivrosOutboundPort) {
        this.criacaoEmprestimoOutboundPort = criacaoEmprestimoOutboundPort;
        this.consultaEmprestimoOutboundPort = consultaEmprestimoOutboundPort;
        this.consultaUsuariosOutboundPort = consultaUsuariosOutboundPort;
        this.consultaLivrosOutboundPort = consultaLivrosOutboundPort;
    }

    @Override
    public Emprestimo criar(Emprestimo emprestimo) {
        Usuario usuario = consultaUsuariosOutboundPort.consultarPorId(emprestimo.getUsuario().getId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(emprestimo.getUsuario().getId(), Usuario.class));

        Livro livro = consultaLivrosOutboundPort.consultarPorId(emprestimo.getLivro().getId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(emprestimo.getLivro().getId(), Livro.class));

        boolean livroJaEmprestado = consultaEmprestimoOutboundPort.verificarExistenciaEmprestimo(livro);

        if (livroJaEmprestado) {
            throw new RegraNegocioException("Livro '%s' indisponível no momento. Tente novamente outro dia.".formatted(livro.getTitulo()));
        }

        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);

        return criacaoEmprestimoOutboundPort.criar(emprestimo);
    }
}
