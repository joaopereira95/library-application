package br.com.elotech.oxy.library.infrastructure.adapters.outbound.database.emprestimos.repository;

import br.com.elotech.oxy.library.domain.enums.StatusEmprestimo;
import br.com.elotech.oxy.library.domain.models.entities.Emprestimo;
import br.com.elotech.oxy.library.domain.models.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer> {

    boolean existsByLivroAndStatus(Livro livro, StatusEmprestimo status);

    List<Emprestimo> findAllByOrderByIdDesc();

}
