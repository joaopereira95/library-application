package br.com.elotech.oxy.library.application.mappers;

import br.com.elotech.oxy.library.application.dtos.input.LivroRequest;
import br.com.elotech.oxy.library.application.dtos.output.LivroOnlineResponse;
import br.com.elotech.oxy.library.application.dtos.output.LivroResponse;
import br.com.elotech.oxy.library.domain.models.entities.Categoria;
import br.com.elotech.oxy.library.domain.models.entities.Livro;
import br.com.elotech.oxy.library.domain.models.LivroOnline;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LivroMapper {

    public Livro toModel(LivroRequest livroRequest) {
        return new Livro(
                livroRequest.titulo(),
                livroRequest.autor(),
                livroRequest.isbn(),
                livroRequest.dataPublicacao(),
                new Categoria(livroRequest.codigoCategoria())
        );
    }

    public List<LivroResponse> toLivroResponseList(List<Livro> livros) {
        return livros.stream().map(this::toLivroResponse).toList();
    }

    public List<LivroOnlineResponse> toLivroOnlineResponseList(List<LivroOnline> livros) {
        return livros.stream().map(this::toLivroOnlineResponse).toList();
    }

    public LivroResponse toLivroResponse(Livro livro) {
        return new LivroResponse(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getIsbn(),
                livro.getDataPublicacao(),
                livro.getCategoria()
        );
    }

    public LivroOnlineResponse toLivroOnlineResponse(LivroOnline livro) {
        return new LivroOnlineResponse(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getIsbn(),
                livro.getDataPublicacao(),
                livro.getCategoria()
        );
    }
}
