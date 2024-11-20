package br.com.elotech.oxy.library.infrastructure.adapters.outbound.database.livros.repository;

import br.com.elotech.oxy.library.domain.models.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {

    List<Livro> findAllByOrderByIdDesc();

    @Query("""
                SELECT livro
                FROM Livro livro
                WHERE livro.categoria.id IN (
                    SELECT emprestimo.livro.categoria.id
                    FROM Emprestimo emprestimo
                    WHERE emprestimo.usuario.id = :usuarioId
                )
                AND livro.id NOT IN (
                    SELECT emprestimo.livro.id
                    FROM Emprestimo emprestimo
                    WHERE emprestimo.usuario.id = :usuarioId
                )
            """)
    List<Livro> buscarRecomendacoesDeLivros(@Param("usuarioId") Integer usuarioId);

    Optional<Livro> findByIsbn(String isbn);

}
