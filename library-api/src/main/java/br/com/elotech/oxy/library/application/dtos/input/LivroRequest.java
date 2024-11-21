package br.com.elotech.oxy.library.application.dtos.input;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record LivroRequest(

        @NotBlank
        @Size(min = 1, max = 500)
        String titulo,

        @NotBlank
        @Size(min = 1, max = 300)
        String autor,

        @NotBlank
        @Size(min = 1, max = 13)
        String isbn,

        @NotNull
        @PastOrPresent
        LocalDate dataPublicacao,

        @NotNull
        @Min(1)
        Integer codigoCategoria)

{ }
