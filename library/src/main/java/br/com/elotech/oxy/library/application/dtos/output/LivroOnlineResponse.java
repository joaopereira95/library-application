package br.com.elotech.oxy.library.application.dtos.output;

import br.com.elotech.oxy.library.domain.models.entities.Categoria;

import java.time.LocalDate;

public record LivroOnlineResponse(
        String id,
        String titulo,
        String autor,
        String isbn,
        LocalDate dataPublicacao,
        Categoria categoria) {
}