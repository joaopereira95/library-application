package br.com.elotech.oxy.library.application.dtos.output;

import java.time.LocalDate;

public record UsuarioResponse(
        Integer id,
        String nome,
        String email,
        LocalDate dataCadastro,
        String telefone) {}