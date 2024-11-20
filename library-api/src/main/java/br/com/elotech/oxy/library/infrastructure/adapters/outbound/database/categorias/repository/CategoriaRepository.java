package br.com.elotech.oxy.library.infrastructure.adapters.outbound.database.categorias.repository;

import br.com.elotech.oxy.library.domain.models.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    Optional<Categoria> findFirstByNome(String nome);

    List<Categoria> findAllByOrderByIdDesc();


}