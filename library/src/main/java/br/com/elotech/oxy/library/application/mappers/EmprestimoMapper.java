package br.com.elotech.oxy.library.application.mappers;

import br.com.elotech.oxy.library.application.dtos.input.EmprestimoRequest;
import br.com.elotech.oxy.library.application.dtos.output.EmprestimoResponse;
import br.com.elotech.oxy.library.domain.models.entities.Emprestimo;
import br.com.elotech.oxy.library.domain.models.entities.Livro;
import br.com.elotech.oxy.library.domain.models.entities.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmprestimoMapper {

    private final UsuarioMapper usuarioMapper;
    private final LivroMapper livroMapper;

    public EmprestimoMapper(UsuarioMapper usuarioMapper, LivroMapper livroMapper) {
        this.usuarioMapper = usuarioMapper;
        this.livroMapper = livroMapper;
    }

    public Emprestimo toModel(EmprestimoRequest emprestimoRequest) {
        return new Emprestimo(
                new Usuario(emprestimoRequest.codigoUsuario()),
                new Livro(emprestimoRequest.codigoLivro()),
                emprestimoRequest.dataEmprestimo()
        );
    }

    public List<EmprestimoResponse> toEmprestimoResponseList(List<Emprestimo> emprestimos) {
        return emprestimos.stream().map(this::toEmprestimoResponse).toList();
    }

    public EmprestimoResponse toEmprestimoResponse(Emprestimo emprestimo) {
        return new EmprestimoResponse(
                emprestimo.getId(),
                usuarioMapper.toUsuarioResponse(emprestimo.getUsuario()),
                livroMapper.toLivroResponse(emprestimo.getLivro()),
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataDevolucao(),
                emprestimo.getStatus()
        );
    }
}
