package br.com.elotech.oxy.library.application.dtos.output;

import br.com.elotech.oxy.library.domain.enums.StatusEmprestimo;

import java.time.LocalDateTime;

public record EmprestimoResponse(

        Integer id,
        UsuarioResponse usuario,
        LivroResponse livro,
        LocalDateTime dataEmprestimo,
        LocalDateTime dataDevolucao,
        StatusEmprestimo status

) { }
