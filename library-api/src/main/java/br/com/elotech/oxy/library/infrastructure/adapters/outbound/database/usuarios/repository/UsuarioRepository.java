package br.com.elotech.oxy.library.infrastructure.adapters.outbound.database.usuarios.repository;

import br.com.elotech.oxy.library.domain.models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> findAllByOrderByIdDesc();

}
