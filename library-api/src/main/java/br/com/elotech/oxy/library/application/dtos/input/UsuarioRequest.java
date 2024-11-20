package br.com.elotech.oxy.library.application.dtos.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioRequest(

        @NotBlank
        @Size(min = 1, max = 200)
        String nome,

        @NotBlank
        @Size(min = 1, max = 250)
        @Email
        String email,

        @NotBlank
        @Size(min = 1, max = 11)
        String telefone

) {
}
