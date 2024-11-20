package br.com.elotech.oxy.library.application.dtos.input;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

public record EmprestimoRequest(

        @NotNull
        @Min(1)
        Integer codigoUsuario,

        @NotNull
        @Min(1)
        Integer codigoLivro,

        @NotNull
        @PastOrPresent
        LocalDateTime dataEmprestimo

) {
}
